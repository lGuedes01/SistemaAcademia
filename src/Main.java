class Main {
   public static void main(String args[]){
       try{
           ConexaoMySQL.abrir().close();
       } catch (Exception e){
           System.out.println(e.getMessage());
           System.exit(-1);
       }
       MenuNovo.menuPrincipal();
   }
}
