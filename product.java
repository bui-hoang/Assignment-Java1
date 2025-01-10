import static java.lang.System.out;
import static java.lang.System.console;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

class Product {
    String name;
    double price;
    int quantity;
    char size;
    boolean isActive;
    Date dateImport;
}

Product [] array = new Product[0];

void main() {
    loop(); // event
}

void loop() {
    gán();

    while (true) {
        try {
            menu();
        } catch (Exception e) {
            // TODO: handle exception (Xử lý ngoại lệ, lỗi dị thường)
        }
        out.print("\n Bạn có muốn tiếp tục không?");
        out.print("\n Ấn K để dừng chương trình: ");
        char ck = console().readLine().charAt(0);

        if (ck == 'k' || ck == 'K') //  Người dùng chọn dừng / thoát
        {
            out.print("\n Cảm ơn bạn đã sử dụng chương trình, Tạm biệt!");
            System.exit(0);
        } else 
            continue;
    }
}

void menu() {
    out.println("+-------------------------------------------------+");
        out.println("|                 QUẢN LÝ SẢN PHẨM               |");
        out.println("+-------------------------------------------------+");
        out.println("|      1.Thêm      |     2.Sửa      |    3.Xuất   |");
        out.println("+-------------------------------------------------+");
        out.println("|      4.Xoá       |     0.Thoát    |             |");
        out.println("+-------------------------------------------------+");

        out.print("\n Chọn menu: ");
        var menu = Integer.parseInt(console().readLine());

        switch (menu) {
            case 1:
                nhập();
                break;
            case 2:
                sửa();
                break;
            case 3:
                xuất();
                break;
            case 4:
                xoá();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                out.println("Bạn vui lòng chọn menu hợp lệ [0-4]");
                break;
        }
    }

void gán() // Assign
{
    var prd1 = new Product();
        prd1.name = "Áo len";
        prd1.price = 60;
        prd1.quantity = 32;
        prd1.size = 'M';
        prd1.isActive = true;
        prd1.dateImport = Date.valueOf("2001-12-22");

        var prd2 = new Product();
        prd2.name = "Quần dài";
        prd2.price = 80;
        prd2.quantity = 12;
        prd2.size = 'S';
        prd2.isActive = true;
        prd2.dateImport = Date.valueOf("2001-12-22");

        var prd3 = new Product();
        prd3.name = "Mũ";
        prd3.price = 20;
        prd3.quantity = 9;
        prd3.size = 'S';
        prd3.isActive = false;
        prd3.dateImport = Date.valueOf("2001-12-22");

        array = new Product[]{prd1, prd2, prd3};
        out.print("\n Đã xong việc gán dữ liệu tĩnh vào mảng!");
}

void nhập() {
    Scanner scan = new Scanner(System.in).useLocale(Locale.US);
    int độ_dài;
    out.print("\n Nhập độ dài mảng: ");
    độ_dài = scan.nextInt();
    array = new Product[độ_dài];
    for (int i = 0; i < array.length; i++) {
        var prd = new Product();
        out.printf("\n Nhập dữ liệu cho phần tử array[%d]: ", i);
        out.print("Nhập tên sản phẩm: ");
        prd.name = scan.next();
        out.print("\n Nhập giá sản phẩm: ");
        prd.price = scan.nextDouble();
        out.print("\n Nhập số lượng sản phẩm: ");
        prd.quantity = scan.nextInt();
        out.print("\n Nhập cỡ sản phẩm (S, M, L): ");
        prd.size = scan.next().charAt(0);
        out.print("\n Sản phẩm có được bán không (true | false): ");
        prd.isActive = scan.nextBoolean();
        out.print("\n Nhập ngày nhập (yyyy-MM-dd): ");
        prd.dateImport = Date.valueOf(scan.next());
        array[i] = prd;
    }
    out.println("\n Đã hoàn tất việc nhập array");
}

void sửa() {
    Scanner scan = new Scanner(System.in);
        out.print("\nNhập tên sản phẩm muốn sửa: ");
        String name = scan.nextLine();

        for (int i = 0; i < array.length; i++) {
            if (array[i].name.equals(name)) {
                out.print("\nNhập giá mới: ");
                array[i].price = scan.nextDouble();
                out.print("\nNhập số lượng mới: ");
                array[i].quantity = scan.nextInt();
                out.print("\nNhập cỡ mới (S, M, L): ");
                array[i].size = scan.next().charAt(0);
                out.print("\nNhập trạng thái bán mới (true/false): ");
                array[i].isActive = scan.nextBoolean();
                out.print("\nNhập ngày nhập mới (yyyy-MM-dd): ");
                array[i].dateImport = Date.valueOf(scan.next());
                out.println("Sản phẩm đã được cập nhật.");
                return;
            }
        }
        out.println("Không tìm thấy sản phẩm với tên: " + name);
}

void xuất() {
    if (array.length == 0) {
        out.println("Danh sách sản phẩm trống.");
        return;
    }
    cột();
    for (int i = 0; i < array.length; i++) {
        dòng(i + 1, array[i]);
    }
}

void cột() {
    out.println("+------------------------------------------------------------------------------------------+");
        out.println("| STT |    Name    |    Price    |    Quantity   |   Size   |   Active   |   Date Import   |");
        out.println("+------------------------------------------------------------------------------------------+");
}

void dòng(int stt, Product dl) {
    out.printf("\n|%2d | %10s | %8.2f | %8d | %4c | %7s | %12s|\n",
    stt, dl.name, dl.price, dl.quantity, dl.size, dl.isActive ? "ĐTN" : "CTN", new SimpleDateFormat("dd/MM/yyyy").format(dl.dateImport));
    out.println("+------------------------------------------------------------------------------------------+");
    
}

void xoá() {
    Scanner scan = new Scanner(System.in);
    out.print("\nNhập tên sản phẩm muốn xóa: ");
    String name = scan.nextLine();

    boolean found = false;
    for (int i = 0; i < array.length; i++) {
        if (array[i].name.equals(name)) {
            for (int j = i; j < array.length - 1; j++) {
                array[j] = array[j + 1];
            }
            array = java.util.Arrays.copyOf(array, array.length - 1);
            found = true;
            out.println("Sản phẩm đã được xóa.");
            break;
        }
    }

    if (!found) {
        out.println("Không tìm thấy sản phẩm với tên: " + name);
    }
}
