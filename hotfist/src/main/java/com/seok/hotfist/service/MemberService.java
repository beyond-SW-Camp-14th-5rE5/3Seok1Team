public class MemberSystem {
    private static Map<String, Member> members = new HashMap<>();
    private static int memberNumberCounter = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. 회원 가입");
            System.out.println("2. 로그인");
            System.out.println("3. 회원 탈퇴");
            System.out.print("원하는 기능을 선택하세요 (1, 2, 3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Enter 버퍼 처리

            switch (choice) {
                case 1:
                    signUp(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    deleteAccount(scanner);
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
