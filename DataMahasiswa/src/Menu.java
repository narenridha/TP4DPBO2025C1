import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.getContentPane().setBackground(Color.WHITE);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JCheckBox fireCheckBox;
    private JCheckBox anginCheckBox;
    private JCheckBox waterCheckBox;
    private JCheckBox tanahCheckBox;
    private JCheckBox petirCheckBox;
    private JLabel Element;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        // isi listMahasiswa
        populateList();


        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                }else{
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex != -1) { // Harus ada data yang dipilih
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    private ArrayList<String> getSelectedElements() {
        ArrayList<String> selectedElements = new ArrayList<>();
        if (fireCheckBox.isSelected()) selectedElements.add("Api");
        if (anginCheckBox.isSelected()) selectedElements.add("Angin");
        if (waterCheckBox.isSelected()) selectedElements.add("Air");
        if (tanahCheckBox.isSelected()) selectedElements.add("Tanah");
        if (petirCheckBox.isSelected()) selectedElements.add("Petir");
        return selectedElements;
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "Nim", "Nama", "Jenis Kelamin", "Element"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        // isi tabel dengan listMahasiswa
        for (int i = 0; i < listMahasiswa.size(); i++) {
            Object[] row = new Object[5];
            row[0] = i + 1;
            row[1] = listMahasiswa.get(i).getNim();
            row[2] = listMahasiswa.get(i).getNama();
            row[3] = listMahasiswa.get(i).getJenisKelamin();
            row[4] = listMahasiswa.get(i).getElementAsString();

            temp.addRow(row);
        }

        return temp; // return juga harus diganti
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        ArrayList<String> elements = getSelectedElements();

        // tambahkan data ke dalam list
        listMahasiswa.add(new Mahasiswa(nim, nama, jenisKelamin, elements));

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");

    }

    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        ArrayList<String> elements = getSelectedElements();

        // ubah data mahasiswa di list
        listMahasiswa.get(selectedIndex).setNim(nim);
        listMahasiswa.get(selectedIndex).setNama(nama);
        listMahasiswa.get(selectedIndex).setJenisKelamin(jenisKelamin);
        listMahasiswa.get(selectedIndex).setElement(elements);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Update Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");

    }

    public void deleteData() {
        // Konfirmasi sebelum menghapus
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Yakin nichhh, mau diapus?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );

        // Jika pengguna memilih "Yes", lanjutkan proses penghapusan
        if (confirm == JOptionPane.YES_OPTION) {
            // Hapus data dari list
            listMahasiswa.remove(selectedIndex);

            // Update tabel
            mahasiswaTable.setModel(setTable());

            // Bersihkan form
            clearForm();

            // Feedback
            System.out.println("Delete Berhasil!");
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } else {
            // Jika memilih "No", batalkan proses hapus
            System.out.println("Penghapusan dibatalkan.");
        }
    }


    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    private void populateList() {
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", new ArrayList<>(List.of("Air"))));
        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki", new ArrayList<>(List.of("Api"))));
        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki", new ArrayList<>(List.of("Spesial")))); // Spesial
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", new ArrayList<>(List.of("Tanah"))));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", new ArrayList<>(List.of("Angin"))));
        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki", new ArrayList<>(List.of("Air", "Angin"))));
        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan", new ArrayList<>(List.of("Api", "Tanah"))));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", new ArrayList<>(List.of("Tanah"))));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", new ArrayList<>(List.of("Angin"))));
        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki", new ArrayList<>(List.of("Api"))));
        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki", new ArrayList<>(List.of("Air"))));
        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan", new ArrayList<>(List.of("Tanah", "Angin"))));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", new ArrayList<>(List.of("Angin"))));
        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki", new ArrayList<>(List.of("Api"))));
        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki", new ArrayList<>(List.of("Tanah"))));
        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki", new ArrayList<>(List.of("Air"))));
        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki", new ArrayList<>(List.of("Api", "Air"))));
        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan", new ArrayList<>(List.of("Angin", "Tanah"))));
        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki", new ArrayList<>(List.of("Api", "Angin"))));
        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan", new ArrayList<>(List.of("Air", "Tanah"))));
    }
}
