public class Main {
    public static void main(String args[]) {

        BDModel Model = new BDModel();
        BDView View = new BDView();
        BDController Controller = new BDController(Model, View);
        View.setVisible(true);


    }
}
