public class Doidona{
    final int TAMT1 = 100;
    final int TAMT3 = 100;
    final int NULO = -0x7FFFFF;

    int[] t1;
    int[] t3;

    ArvoreBinaria arvoreBinaria;
    ListaSimples lista;
    AVL arvoreAVL;

    public Doidona(){
        t1 = new int [TAMT1];
        t3 = new int [TAMT3];

        for(int i = 0; i < TAMT1; i++){
            t1[i] = NULO;
        }
        for(int i = 0; i < TAMT3; i++){
            t3[i] = NULO;
        }

        arvoreBinaria = new ArvoreBinaria();
        arvoreAVL = new AVL();
        lista = new ListaSimples();
    }

    public int hashT1(int elemento){
        return elemento % 4;
    }

    public int hashT2(int elemento){
        return elemento % 3;
    }

    public int hashT3(int elemento){
        return elemento % 2;
    }

    public int rehashT3(int elemento){
        return elemento % 5;
    }

    public void inserir(int elemento) throws Exception {
        int i = hashT1(elemento);
        if(elemento == NULO) {
            //gerar msg de erro para o usuario...
        } else if(t1[i] == NULO){
            t1[i] = elemento;
        }else if(hashT2(elemento) == 0){
            i = hashT3(elemento);
            if(t3[i] == NULO){
                t3[i] = elemento;
            } else {
                i = rehashT3(elemento);
                if(t3[i] == NULO){
                    t3[i] = elemento;
                } else {
                    arvoreBinaria.inserir(elemento);
                }
            }
        }else if (hashT2(elemento) == 1){
            lista.inserirFim(elemento);
        }else if (hashT2(elemento) == 2){
            arvoreAVL.inserir(elemento);
        } else {
            System.out.println("ERRO!!!!");
        }
    }
    void remover (int elemento){
    }

    boolean pesquisar (int elemento){
        boolean resp = false;
        int pos = hashT1(elemento);
        if(elemento == NULO){
            resp = false;
        } else if(t1[pos] == elemento){
            resp = true;
        }else {
            pos = hashT2(elemento);
            if (pos == 0){
                pos = hashT3(elemento);
                if(t3[pos] == elemento){
                    resp = true;
                }else{
                    pos = rehashT3(elemento);
                    if(t3[pos] == elemento){
                        resp = true;
                    }else{
                        resp = arvoreBinaria.pesquisar(elemento);
                    }
                }
            }else if (pos == 1){
                resp = lista.pesquisar(elemento);
            } else {
                resp = arvoreAVL.pesquisar(elemento);
            }
        }
        return resp;
    }

    void mostrar(){
        //t1, t3, arvoreBinaria, lista, arvoreAVL
        for(int i = 0; i < TAMT1; i++){
            if(t1[i] != NULO){
                System.out.println(t1[i]);
            }
        }
        for(int i = 0; i < TAMT3; i++){
            if(t3[i] != NULO){
                System.out.println(t3[i]);
            }
        }
        arvoreBinaria.caminharPre();
        lista.mostrar();
        arvoreAVL.caminharPre();
    }
}