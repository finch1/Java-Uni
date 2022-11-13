
package clnt;

public class Menu extends Thread{

    Display displayOptions = new Display();

    final static String[] mainMenu = {"1 - 2D shapes", "2 - 3D shapes", "3 - Exit"}; //type content

    final static String[] menu2D = {"1 - Rectangle", "2 - Circle", "3 - Triangle", "4 - Send all shapes created", "5 - Receive Shapes", "6 - Back to main"};   //menu 2D content

    final static String[] menu3D = {"1 - Cylinder", "2 - Sphere", "3 - Send all shapes created", "4 - Receive Shapes", "5 - Back to main"};     //menu 3D content

    final static String[] menuRX = {"r - Rectangle", "c - Circle", "t - Triangle", "a - All"};     //menu receive options

    public void displayMenu() { //output menus + user input

        while (true) { //loop through menu until user exit

            displayOptions.iterateMenu(mainMenu);

            switch ((int)displayOptions.getNumInput()){//main menu
                case 1: //2D shapes
                    displayOptions.iterateMenu(menu2D);

                    switch ((int)displayOptions.getNumInput()){
                        case 1: //rectangle
                            displayOptions.getRectangle();
                            break;

                        case 2: //circle
                            displayOptions.getCircle();
                            break;

                        case 3: //triangle
                            displayOptions.getTriangle();
                            break;

                        case 4: //TX
                             displayOptions.shapeTransfer(null, 1);
                            break;

                        case 5: //RX
                            displayOptions.iterateMenu(menuRX); //specify shape or all from server
                            displayOptions.shapeTransfer(displayOptions.getStringInput(), 2); //user shape choice before shape transfer
                            break;

                        case 6: //sub-exit
                            System.out.println("To main menu");
                            break; //exit

                        default:
                            System.out.println("value must be between 1 and 6\n");
                            break;
                    }//2D switch
                    break; //case 1 main menu

                case 2: //3D shapes
                    displayOptions.iterateMenu(menu3D);

                    switch ((int)displayOptions.getNumInput()){
                        case 1: //rectangle
                            displayOptions.getCylinder();
                            break;

                        case 2: //circle
                            displayOptions.getSphere();
                            break;

                        case 3: //TX
                            displayOptions.shapeTransfer(null, 1);
                            break;

                        case 4: //RX
                            displayOptions.shapeTransfer("a", 2); //user shape choice before shape transfer
                            break;

                        case 5: //sub-exit
                            System.out.println("To main menu");
                            break; //exit

                        default:
                            System.out.println("value must be between 1 and 5\n");
                            break;
                    }//2D switch
                    break;//3D Case

                case 3:
                    System.out.println("Bye!!"); //exit main menu
                    System.exit(0);
                    break; //exit

                default:
                    System.out.println("value must be between 1 and 3\n");
                    break;
            }//switch

        }//while
    }//run

}//Menu







