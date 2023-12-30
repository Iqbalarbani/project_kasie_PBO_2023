import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    private int nomorMenu;
    private String namaMenu;
    private double harga;

    public Menu(int nomorMenu, String namaMenu, double harga) {
        this.nomorMenu = nomorMenu;
        this.namaMenu = namaMenu;
        this.harga = harga;
    }

    public int getNomorMenu() {
        return nomorMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public double getHarga() {
        return harga;
    }
}

class Pesanan {
    private static int nomorPelanggan = 1;
    private int nomorPesanan;
    ArrayList<Menu> pesananMakanan;
    ArrayList<Menu> pesananMinuman;

    public Pesanan() {
        this.nomorPesanan = nomorPelanggan++;
        this.pesananMakanan = new ArrayList<>();
        this.pesananMinuman = new ArrayList<>();
    }

    public int getNomorPesanan() {
        return nomorPesanan;
    }

    public void tambahPesananMakanan(Menu menu) {
        pesananMakanan.add(menu);
    }

    public void tambahPesananMinuman(Menu menu) {
        pesananMinuman.add(menu);
    }

    public double hitungTotalHarga() {
        double totalHarga = 0;
        for (Menu menu : pesananMakanan) {
            totalHarga += menu.getHarga();
        }
        for (Menu menu : pesananMinuman) {
            totalHarga += menu.getHarga();
        }
        return totalHarga;
    }
}

public class PemesananMakanan {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Menu> daftarMenu = inisialisasiMenu();
    private static ArrayList<Pesanan> pesananList = new ArrayList<>();

    public static void main(String[] args) {
        tampilkanMenu();
        pesanMakanan();
        tampilkanPesanan();
    }

    private static ArrayList<Menu> inisialisasiMenu() {
        ArrayList<Menu> daftarMenu = new ArrayList<>();

        // Menambahkan menu makanan
        daftarMenu.add(new Menu(1, "Nasi Goreng  ", 20_000));
        daftarMenu.add(new Menu(2, "Mie Goreng   ", 15_000));
        daftarMenu.add(new Menu(3, "Ayam Bakar   ", 15_000));
        daftarMenu.add(new Menu(4, "Nasi Ayam    ", 12_000));
        daftarMenu.add(new Menu(5, "Gudeg Jogja  ", 38_000));
        daftarMenu.add(new Menu(6, "Bakpia       ", 25_000));
        daftarMenu.add(new Menu(7, "Sate Ayam    ", 10_000));
        daftarMenu.add(new Menu(8, "Sate Sapi    ", 20_000));
        daftarMenu.add(new Menu(9, "Bubur Ayam   ", 10_000));
        daftarMenu.add(new Menu(10, "Nasi bebek  ", 19_000));

        // Menambahkan menu minuman

        daftarMenu.add(new Menu(11, "Es Teh      ", 5_000));
        daftarMenu.add(new Menu(12, "Jus Jeruk   ", 8_000));
        daftarMenu.add(new Menu(13, "Jus Alpukat ", 10_000));
        daftarMenu.add(new Menu(14, "Wedang Jahe ", 10_000));
        daftarMenu.add(new Menu(15, "Air Mineral ", 3_000));
        // ... (tambahkan menu minuman lainnya)

        return daftarMenu;
    }

    private static void tampilkanMenu() {
        System.out.println("Daftar Menu Makanan:");
        for (Menu menu : daftarMenu) {
            System.out.println(menu.getNomorMenu() + ". " + menu.getNamaMenu() + " - Rp. " + menu.getHarga());
        }
    }

    private static void pesanMakanan() {
        Pesanan pesanan = new Pesanan();
        char pesanLagi = 0;

        do {
            System.out.print("\nMasukkan nomor menu yang ingin dipesan: ");
            int nomorMenu = scanner.nextInt();

            // Validasi nomor menu
            if (nomorMenu < 1 || nomorMenu > daftarMenu.size()) {
                System.out.println("Nomor menu tidak valid!");
                continue;
            }

            Menu menuDipesan = daftarMenu.get(nomorMenu - 1);
            if (nomorMenu <= 10) {
                pesanan.tambahPesananMakanan(menuDipesan);
            } else {
                pesanan.tambahPesananMinuman(menuDipesan);
            }

            System.out.print("Apakah ingin memesan lagi? (y/n): ");
            pesanLagi = scanner.next().charAt(0);
        } while (pesanLagi == 'y' || pesanLagi == 'Y');

        pesananList.add(pesanan);
    }

    private static void tampilkanPesanan() {
        System.out.println("\nPesanan Anda:");
        for (Pesanan pesanan : pesananList) {
            System.out.println("Nomor Pelanggan: " + pesanan.getNomorPesanan());
            System.out.println("Pesanan Makanan:");
            for (Menu menu : pesanan.pesananMakanan) {
                System.out.println("- " + menu.getNamaMenu() + " - Rp. " + menu.getHarga());
            }
            System.out.println("Pesanan Minuman:");
            for (Menu menu : pesanan.pesananMinuman) {
                System.out.println("- " + menu.getNamaMenu() + " - Rp. " + menu.getHarga());
            }
            System.out.println("Total Harga: Rp. " + pesanan.hitungTotalHarga());
            System.out.println("--------------------");
        }
    }
}
