package Week1;

import java.util.Scanner;
//100, 50, 20, 10, 5, 2 e 1


//100, 50, 20, 10, 5, 2 e 1
public class Banknotes1018 {
    public static final int A = 100;
    public static final int B = 50;
    public static final int C = 20;
    public static final int D = 10;
    public static final int E = 5;
    public static final int F = 2;
    public static final int G = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        int result = 0;
        int hundredNote = 0;
        int fiftyNote = 0;
        int twentyNote = 0;
        int tenNote = 0;
        int fiveNote = 0;
        int twoNote = 0;
        int oneNote = 0;

        //1193
        if(input > 100){
            hundredNote = input / A;
            result = input % A;
            if (result > 50) {
                fiftyNote = result / B;
                result %= B;
                if (result > 20) {
                    twentyNote = result / C;
                    result %= C;
                    if (result > 10) {
                        tenNote = result / D;
                        result %= D;
                        if (result > 5) {
                            fiveNote = result / E;
                            result %= E;
                            if (result > 2) {
                                twoNote = result / F;
                                result %= F;
                                if (result == 0) {
                                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                                }
                                if (result == 1) {
                                    oneNote++;
                                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                                }
                                if(result == 2) {
                                    twoNote++;
                                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                                }
                            }
                        }
                    }
                }
                if (result > 20) {
                    twentyNote = result / C;
                    result %= C;
                    if (result > 10) {
                        tenNote = result / D;
                        result %= D;
                        if (result > 5) {
                            fiveNote = result / E;
                            result %= E;
                            if (result > 2) {
                                twoNote = result / F;
                                result %= F;
                                if (result == 0) {
                                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                                }
                                if (result == 1) {
                                    oneNote++;
                                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                                }
                                if(result == 2) {
                                    twoNote++;
                                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                                }
                            }
                        }
                    }
                }
                if (result > 10) {
                    tenNote = result / D;
                    result %= D;
                    if (result > 5) {
                        fiveNote = result / E;
                        result %= E;
                        if (result > 2) {
                            twoNote = result / F;
                            result %= F;
                            if (result == 0) {
                                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                            }
                            if (result == 1) {
                                oneNote++;
                                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                            }
                            if(result == 2) {
                                twoNote++;
                                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                            }
                        }
                    }
                }
                if (result > 5) {
                    fiveNote = result / E;
                    result %= E;
                    if (result > 2) {
                        twoNote = result / F;
                        result %= F;
                        if (result == 0) {
                            printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                        }
                        if (result == 1) {
                            oneNote++;
                            printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                        }
                        if(result == 2) {
                            twoNote++;
                            printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                        }
                    }
                }
                if (result > 2) {
                    twoNote = result / F;
                    result %= F;
                    if (result == 0) {
                        printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                    }
                    if (result == 1) {
                        oneNote++;
                        printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                    }
                    if(result == 2) {
                        twoNote++;
                        printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                    }
                }
                if (result == 0) {
                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                }
                if (result == 1) {
                    oneNote++;
                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                }
                if(result == 2) {
                    twoNote++;
                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                }
            }
        }
        if (result > 50 && result < 100) {
            fiftyNote = result / B;
            result %= B;
            if (result > 20) {
                twentyNote = result / C;
                result %= C;
                if (result > 10) {
                    tenNote = result / D;
                    result %= D;
                    if (result > 5) {
                        fiveNote = result / E;
                        result %= E;
                        if (result > 2) {
                            twoNote = result / F;
                            result %= F;
                            if (result == 0) {
                                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                            }
                            if (result == 1) {
                                oneNote++;
                                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                            }
                            if(result == 2) {
                                twoNote++;
                                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                            }
                        }
                    }
                }
            }
            if (result > 20) {
                twentyNote = result / C;
                result %= C;
                if (result > 10) {
                    tenNote = result / D;
                    result %= D;
                    if (result > 5) {
                        fiveNote = result / E;
                        result %= E;
                        if (result > 2) {
                            twoNote = result / F;
                            result %= F;
                            if (result == 0) {
                                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                            }
                            if (result == 1) {
                                oneNote++;
                                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                            }
                            if(result == 2) {
                                twoNote++;
                                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                            }
                        }
                    }
                }
            }
            if (result > 10) {
                tenNote = result / D;
                result %= D;
                if (result > 5) {
                    fiveNote = result / E;
                    result %= E;
                    if (result > 2) {
                        twoNote = result / F;
                        result %= F;
                        if (result == 0) {
                            printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                        }
                        if (result == 1) {
                            oneNote++;
                            printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                        }
                        if(result == 2) {
                            twoNote++;
                            printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                        }
                    }
                }
            }
            if (result > 5) {
                fiveNote = result / E;
                result %= E;
                if (result > 2) {
                    twoNote = result / F;
                    result %= F;
                    if (result == 0) {
                        printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                    }
                    if (result == 1) {
                        oneNote++;
                        printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                    }
                    if(result == 2) {
                        twoNote++;
                        printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                    }
                }
            }
            if (result > 2) {
                twoNote = result / F;
                result %= F;
                if (result == 0) {
                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                }
                if (result == 1) {
                    oneNote++;
                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                }
                if(result == 2) {
                    twoNote++;
                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                }
            }
            if (result == 0) {
                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
            }
            if (result == 1) {
                oneNote++;
                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
            }
            if(result == 2) {
                twoNote++;
                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
            }
        }
        if (result > 20 && result < 50) {
            twentyNote = result / C;
            result %= C;
            if (result > 10) {
                tenNote = result / D;
                result %= D;
                if (result > 5) {
                    fiveNote = result / E;
                    result %= E;
                    if (result > 2) {
                        twoNote = result / F;
                        result %= F;
                        if (result == 0) {
                            printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                        }
                        if (result == 1) {
                            oneNote++;
                            printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                        }
                        if(result == 2) {
                            twoNote++;
                            printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                        }
                    }
                }
            }
        }
        if (result > 10 && result < 20) {
            tenNote = result / D;
            result %= D;
            if (result > 5) {
                fiveNote = result / E;
                result %= E;
                if (result > 2) {
                    twoNote = result / F;
                    result %= F;
                    if (result == 0) {
                        printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                    }
                    if (result == 1) {
                        oneNote++;
                        printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                    }
                    if(result == 2) {
                        twoNote++;
                        printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                    }
                }
            }
        }
        if (result > 5 && result < 10) {
            fiveNote = result / E;
            result %= E;
            if (result > 2) {
                twoNote = result / F;
                result %= F;
                if (result == 0) {
                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                }
                if (result == 1) {
                    oneNote++;
                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                }
                if(result == 2) {
                    twoNote++;
                    printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
                }
            }
        }
        if (result > 2 && result < 5) {
            twoNote = result / F;
            result %= F;
            if (result == 0) {
                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
            }
            if (result == 1) {
                oneNote++;
                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
            }
            if(result == 2) {
                twoNote++;
                printResult(input, hundredNote, fiftyNote, twentyNote, tenNote, fiveNote, twoNote, oneNote);
            }
        }
    }
    public static void printResult(int input, int hundredNote, int fiftyNote, int twentyNote, int tenNote, int fiveNote, int twoNote, int oneNote){
        System.out.println(input);
        System.out.printf(hundredNote + " nota(s) de R$ 100,00\n");
        System.out.printf(fiftyNote + " nota(s) de R$ 50,00\n");
        System.out.printf(twentyNote + " nota(s) de R$ 20,00\n");
        System.out.printf(tenNote + " nota(s) de R$ 10,00\n");
        System.out.printf(fiveNote + " nota(s) de R$ 5,00\n");
        System.out.printf(twoNote + " nota(s) de R$ 2,00\n");
        System.out.printf(oneNote + " nota(s) de R$ 1,00\n");
    }
}
