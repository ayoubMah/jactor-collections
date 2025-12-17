package ayoub;


import ayoub.list.MyArrayList;

public class Main {
    //======================================================================================================================
    public static final String PURPLE_BRIGHT = "\u001B[95m";
    public static final String YELLOW_BRIGHT = "\u001B[93m";
    public static final String CYAN_BRIGHT = "\u001B[96m";
    public static final String RESET = "\u001B[0m";
    public static final String BORDER = CYAN_BRIGHT + "≫" + RESET;
    private static String createBorder(int length, String symbol) {
        StringBuilder border = new StringBuilder();
        for (int i = 0; i < length; i++) { border.append(symbol);}
        return border.toString();
    }
    public static void main(String[] args) {
        System.out.println();
        System.out.println("  " + createBorder(110, BORDER));
        System.out.println(PURPLE_BRIGHT +
                " ███╗   ███╗██╗   ██╗     ██████╗ ██████╗ ██╗     ██╗     ███████╗ ██████╗████████╗██╗ ██████╗ ███╗   ██╗" + RESET);
        System.out.println(YELLOW_BRIGHT +
                " ████╗ ████║╚██╗ ██╔╝    ██╔════╝██╔═══██╗██║     ██║     ██╔════╝██╔════╝╚══██╔══╝██║██╔═══██╗████╗  ██║" + RESET);
        System.out.println(PURPLE_BRIGHT +
                " ██╔████╔██║ ╚████╔╝     ██║     ██║   ██║██║     ██║     █████╗  ██║        ██║   ██║██║   ██║██╔██╗ ██║" + RESET);
        System.out.println(YELLOW_BRIGHT +
                " ██║╚██╔╝██║  ╚██╔╝      ██║     ██║   ██║██║     ██║     ██╔══╝  ██║        ██║   ██║██║   ██║██║╚██╗██║" + RESET);
        System.out.println(PURPLE_BRIGHT +
                " ██║ ╚═╝ ██║   ██║       ╚██████╗╚██████╔╝███████╗███████╗███████╗╚██████╗   ██║   ██║╚██████╔╝██║ ╚████║" + RESET);
        System.out.println(YELLOW_BRIGHT +
                " ╚═╝     ╚═╝   ╚═╝        ╚═════╝ ╚═════╝ ╚══════╝╚══════╝╚══════╝ ╚═════╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝" + RESET);
        System.out.println("  " + createBorder(110, BORDER));
        // ========================================================================================

        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add(1,"ayoub");

        System.out.println("List size: " + list.size());
        System.out.println(list);
        System.out.println("let's remove B");
        list.remove(2);
        System.out.println(list);
    }
}
