public class Main {
    public static void main(String args[]) {

        BDModel Model = new BDModel();
        BDView View = new BDView();
        BDLoginWindow LoginWindow = View.createLoginDialog();
        BDController Controller = new BDController(Model, View, LoginWindow);
        View.setVisible(true);


    }
}
