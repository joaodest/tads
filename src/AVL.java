/**
 * Arvore binaria de pesquisa
 * @author Max do Val Machado
 */

public class AVL {
    private NoAVL raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public AVL() {
        raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * @param x Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * @param x Elemento que sera procurado.
     * @param i NoAVL em analise.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    private boolean pesquisar(int x, NoAVL i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (x == i.elemento) {
            resp = true;
        } else if (x < i.elemento) {
            resp = pesquisar(x, i.esq);
        } else {
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i NoAVL em analise.
     */
    private void caminharCentral(NoAVL i) {
        if (i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento + " "); // Conteudo do NoAVL.
            caminharCentral(i.dir); // Elementos da direita.
        }
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPre() {
        System.out.print("[ ");
        caminharPre(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i NoAVL em analise.
     */
    private void caminharPre(NoAVL i) {
        if (i != null) {
            System.out.print(i.elemento + "(fator " + (NoAVL.getNivel(i.dir) - NoAVL.getNivel(i.esq)) + ") "); // Conteudo do NoAVL.
            caminharPre(i.esq); // Elementos da esquerda.
            caminharPre(i.dir); // Elementos da direita.
        }
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPos() {
        System.out.print("[ ");
        caminharPos(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i NoAVL em analise.
     */
    private void caminharPos(NoAVL i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do NoAVL.
        }
    }

    /**
     * Metodo publico iterativo para inserir elemento.
     * @param x Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * @param x Elemento a ser inserido.
     * @param i NoAVL em analise.
     * @return NoAVL em analise, alterado ou nao.
     * @throws Exception Se o elemento existir.
     */
    private NoAVL inserir(int x, NoAVL i) throws Exception {
        if (i == null) {
            i = new NoAVL(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }
        return balancear(i);
    }

    /**
     * Metodo publico iterativo para remover elemento.
     * @param x Elemento a ser removido.
     * @throws Exception Se nao encontrar elemento.
     */
    public void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    /**
     * Metodo privado recursivo para remover elemento.
     * @param x Elemento a ser removido.
     * @param i NoAVL em analise.
     * @return NoAVL em analise, alterado ou nao.
     * @throws Exception Se nao encontrar elemento.
     */
    private NoAVL remover(int x, NoAVL i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao remover!");
        } else if (x < i.elemento) {
            i.esq = remover(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = remover(x, i.dir);
            // Sem NoAVL a direita.
        } else if (i.dir == null) {
            i = i.esq;
            // Sem NoAVL a esquerda.
        } else if (i.esq == null) {
            i = i.dir;
            // NoAVL a esquerda e NoAVL a direita.
        } else {
            i.esq = maiorEsq(i, i.esq);
        }
        return balancear(i);
    }

    /**
     * Metodo para trocar o elemento "removido" pelo maior da esquerda.
     * @param i NoAVL que teve o elemento removido.
     * @param j NoAVL da subarvore esquerda.
     * @return NoAVL em analise, alterado ou nao.
     */
    private NoAVL maiorEsq(NoAVL i, NoAVL j) {
        // Encontrou o maximo da subarvore esquerda.
        if (j.dir == null) {
            i.elemento = j.elemento; // Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.
            // Existe NoAVL a direita.
        } else {
            // Caminha para direita.
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    private NoAVL balancear(NoAVL NoAVL) throws Exception {
        if (NoAVL != null) {
            int fator = NoAVL.getNivel(NoAVL.dir) - NoAVL.getNivel(NoAVL.esq);
            // Se balanceada
            if (Math.abs(fator) <= 1) {
                NoAVL.setNivel();
                // Se desbalanceada para a direita
            } else if (fator == 2) {
                int fatorFilhoDir = NoAVL.getNivel(NoAVL.dir.dir) - NoAVL.getNivel(NoAVL.dir.esq);
                // Se o filho a direita tambem estiver desbalanceado
                if (fatorFilhoDir == -1) {
                    NoAVL.dir = rotacionarDir(NoAVL.dir);
                }
                NoAVL = rotacionarEsq(NoAVL);
                // Se desbalanceada para a esquerda
            } else if (fator == -2) {
                int fatorFilhoEsq = NoAVL.getNivel(NoAVL.esq.dir) - NoAVL.getNivel(NoAVL.esq.esq);
                // Se o filho a esquerda tambem estiver desbalanceado
                if (fatorFilhoEsq == 1) {
                    NoAVL.esq = rotacionarEsq(NoAVL.esq);
                }
                NoAVL = rotacionarDir(NoAVL);
            } else {
                throw new Exception(
                        "Erro NoAVL NoAVL(" + NoAVL.elemento + ") com fator de balanceamento (" + fator + ") invalido!");
            }
        }
        return NoAVL;
    }

    private NoAVL rotacionarDir(NoAVL NoAVL) {
        System.out.println("Rotacionar DIR(" + NoAVL.elemento + ")");
        NoAVL NoAVLEsq = NoAVL.esq;
        NoAVL NoAVLEsqDir = NoAVLEsq.dir;

        NoAVLEsq.dir = NoAVL;
        NoAVL.esq = NoAVLEsqDir;
        NoAVL.setNivel(); // Atualizar o nivel do NoAVL
        NoAVLEsq.setNivel(); // Atualizar o nivel do NoAVLEsq

        return NoAVLEsq;
    }

    private NoAVL rotacionarEsq(NoAVL NoAVL) {
        System.out.println("Rotacionar ESQ(" + NoAVL.elemento + ")");
        NoAVL NoAVLDir = NoAVL.dir;
        NoAVL NoAVLDirEsq = NoAVLDir.esq;

        NoAVLDir.esq = NoAVL;
        NoAVL.dir = NoAVLDirEsq;

        NoAVL.setNivel(); // Atualizar o nivel do NoAVL
        NoAVLDir.setNivel(); // Atualizar o nivel do NoAVLDir
        return NoAVLDir;
    }


}

/**
 * NoAVL da arvore binaria
 * @author Max do Val Machado
 */
class NoAVL {
    public int elemento; // Conteudo do NoAVL.
    public NoAVL esq, dir; // Filhos da esq e dir.
    public int nivel; // Numero de niveis abaixo do NoAVL

    /**
     * Construtor da classe
     * @param elemento Conteudo do NoAVL.
     */
    public NoAVL(int elemento) {
        this(elemento, null, null, 1);
    }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do NoAVL.
     * @param esq      NoAVL da esquerda.
     * @param dir      NoAVL da direita.
     */
    public NoAVL(int elemento, NoAVL esq, NoAVL dir, int nivel) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.nivel = nivel;
    }

    /**
     * Cálculo do número de níveis a partir de um vértice
     */
    public void setNivel() {
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }

    /**
     * Retorna o número de níveis a partir de um vértice
     * @param NoAVL nó que se deseja o nível.
     */
    public static int getNivel(NoAVL NoAVL) {
        return (NoAVL == null) ? 0 : NoAVL.nivel;
    }
}