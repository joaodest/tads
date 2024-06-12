
public class ArvoreBinaria {
    private NoBinaria raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public ArvoreBinaria() {
        raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     *
     * @param x Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     *
     * @param x Elemento que sera procurado.
     * @param i NoBinaria em analise.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    private boolean pesquisar(int x, NoBinaria i) {
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
     *
     * @param i NoBinaria em analise.
     */
    private void caminharCentral(NoBinaria i) {
        if (i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento + " "); // Conteudo do NoBinaria.
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
     *
     * @param i NoBinaria em analise.
     */
    private void caminharPre(NoBinaria i) {
        if (i != null) {
            System.out.print(i.elemento + " "); // Conteudo do NoBinaria.
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
     *
     * @param i NoBinaria em analise.
     */
    private void caminharPos(NoBinaria i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do NoBinaria.
        }
    }


    /**
     * Metodo publico iterativo para inserir elemento.
     *
     * @param x Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     *
     * @param x Elemento a ser inserido.
     * @param i NoBinaria em analise.
     * @return NoBinaria em analise, alterado ou nao.
     * @throws Exception Se o elemento existir.
     */
    private NoBinaria inserir(int x, NoBinaria i) throws Exception {
        if (i == null) {
            i = new NoBinaria(x);

        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }

    /**
     * Metodo publico para inserir elemento.
     *
     * @param x Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserirPai(int x) throws Exception {
        if (raiz == null) {
            raiz = new NoBinaria(x);
        } else if (x < raiz.elemento) {
            inserirPai(x, raiz.esq, raiz);
        } else if (x > raiz.elemento) {
            inserirPai(x, raiz.dir, raiz);
        } else {
            throw new Exception("Erro ao inserirPai!");
        }
    }

    /**
     * Metodo privado recursivo para inserirPai elemento.
     *
     * @param x   Elemento a ser inserido.
     * @param i   NoBinaria em analise.
     * @param pai NoBinaria superior ao em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserirPai(int x, NoBinaria i, NoBinaria pai) throws Exception {
        if (i == null) {
            if (x < pai.elemento) {
                pai.esq = new NoBinaria(x);
            } else {
                pai.dir = new NoBinaria(x);
            }
        } else if (x < i.elemento) {
            inserirPai(x, i.esq, i);
        } else if (x > i.elemento) {
            inserirPai(x, i.dir, i);
        } else {
            throw new Exception("Erro ao inserirPai!");
        }
    }


    /**
     * Metodo publico iterativo para remover elemento.
     *
     * @param x Elemento a ser removido.
     * @throws Exception Se nao encontrar elemento.
     */
    public void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    /**
     * Metodo privado recursivo para remover elemento.
     *
     * @param x Elemento a ser removido.
     * @param i NoBinaria em analise.
     * @return NoBinaria em analise, alterado ou nao.
     * @throws Exception Se nao encontrar elemento.
     */
    private NoBinaria remover(int x, NoBinaria i) throws Exception {

        if (i == null) {
            throw new Exception("Erro ao remover!");

        } else if (x < i.elemento) {
            i.esq = remover(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = remover(x, i.dir);

            // Sem NoBinaria a direita.
        } else if (i.dir == null) {
            i = i.esq;

            // Sem NoBinaria a esquerda.
        } else if (i.esq == null) {
            i = i.dir;

            // NoBinaria a esquerda e NoBinaria a direita.
        } else {
            i.esq = maiorEsq(i, i.esq);
        }

        return i;
    }

    /**
     * Metodo para trocar o elemento "removido" pelo maior da esquerda.
     *
     * @param i NoBinaria que teve o elemento removido.
     * @param j NoBinaria da subarvore esquerda.
     * @return NoBinaria em analise, alterado ou nao.
     */
    private NoBinaria maiorEsq(NoBinaria i, NoBinaria j) {

        // Encontrou o maximo da subarvore esquerda.
        if (j.dir == null) {
            i.elemento = j.elemento; // Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.

            // Existe NoBinaria a direita.
        } else {
            // Caminha para direita.
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    /**
     * Metodo que retorna o maior elemento da árvore
     *
     * @return int maior elemento da árvore
     */
    public int getMaior() {
        int resp = -1;

        if (raiz != null) {
            NoBinaria i;
            for (i = raiz; i.dir != null; i = i.dir) ;
            resp = i.elemento;
        }

        return resp;
    }


    /**
     * Metodo que retorna o meNoBinariar elemento da árvore
     *
     * @return int meNoBinariar elemento da árvore
     */
    public int getMeNoBinariar() {
        int resp = -1;

        if (raiz != null) {
            NoBinaria i;
            for (i = raiz; i.esq != null; i = i.esq) ;
            resp = i.elemento;
        }

        return resp;
    }


    /**
     * Metodo que retorna a altura da árvore
     *
     * @return int altura da árvore
     */
    public int getAltura() {
        return getAltura(raiz, 0);
    }


    /**
     * Metodo que retorna a altura da árvore
     *
     * @return int altura da árvore
     */
    public int getAltura(NoBinaria i, int altura) {
        if (i == null) {
            altura--;
        } else {
            int alturaEsq = getAltura(i.esq, altura + 1);
            int alturaDir = getAltura(i.dir, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
        }
        return altura;
    }


    /**
     * Metodo publico iterativo para remover elemento.
     *
     * @param x Elemento a ser removido.
     * @throws Exception Se nao encontrar elemento.
     */
    public void remover2(int x) throws Exception {
        if (raiz == null) {
            throw new Exception("Erro ao remover2!");
        } else if (x < raiz.elemento) {
            remover2(x, raiz.esq, raiz);
        } else if (x > raiz.elemento) {
            remover2(x, raiz.dir, raiz);
        } else if (raiz.dir == null) {
            raiz = raiz.esq;
        } else if (raiz.esq == null) {
            raiz = raiz.dir;
        } else {
            raiz.esq = maiorEsq(raiz, raiz.esq);
        }
    }

    /**
     * Metodo privado recursivo para remover elemento.
     *
     * @param x   Elemento a ser removido.
     * @param i   NoBinaria em analise.
     * @param pai do NoBinaria em analise.
     * @throws Exception Se nao encontrar elemento.
     */
    private void remover2(int x, NoBinaria i, NoBinaria pai) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao remover2!");
        } else if (x < i.elemento) {
            remover2(x, i.esq, i);
        } else if (x > i.elemento) {
            remover2(x, i.dir, i);
        } else if (i.dir == null) {
            pai = i.esq;
        } else if (i.esq == null) {
            pai = i.dir;
        } else {
            i.esq = maiorEsq(i, i.esq);
        }
    }

    public int getRaiz() throws Exception {
        return raiz.elemento;
    }

    public static boolean igual(ArvoreBinaria a1, ArvoreBinaria a2) {
        return igual(a1.raiz, a2.raiz);
    }

    private static boolean igual(NoBinaria i1, NoBinaria i2) {
        boolean resp;
        if (i1 != null && i2 != null) {
            resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
        } else if (i1 == null && i2 == null) {
            resp = true;
        } else {
            resp = false;
        }
        return resp;
    }

    public int soma() {
        return soma(raiz);
    }

    public int soma(NoBinaria i) {
        int resp = 0;
        if (i != null) {
            resp = i.elemento + soma(i.esq) + soma(i.dir);
        }
        return resp;
    }

    public int quantidadePares() {
        return quantidadePares(raiz);
    }

    public int quantidadePares(NoBinaria i) {
        int resp = 0;
        if (i != null) {
            resp = ((i.elemento % 2 == 0) ? 1 : 0) + quantidadePares(i.esq) + quantidadePares(i.dir);
        }
        return resp;
    }

    public boolean hasDiv11() {
        return hasDiv11(raiz);
    }

    public boolean hasDiv11(NoBinaria i) {
        boolean resp = false;
        if (i != null) {
            resp = (i.elemento % 11 == 0) || hasDiv11(i.esq) || hasDiv11(i.dir);
        }
        return resp;
    }


}

class NoBinaria {
    public int elemento; // Conteudo do NoBinaria.
    public NoBinaria esq, dir;  // Filhos da esq e dir.

    /**
     * Construtor da classe.
     *
     * @param elemento Conteudo do NoBinaria.
     */
    public NoBinaria(int elemento) {
        this(elemento, null, null);
    }

    /**
     * Construtor da classe.
     *
     * @param elemento Conteudo do NoBinaria.
     * @param esq      NoBinaria da esquerda.
     * @param dir      NoBinaria da direita.
     */
    public NoBinaria(int elemento, NoBinaria esq, NoBinaria dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}
