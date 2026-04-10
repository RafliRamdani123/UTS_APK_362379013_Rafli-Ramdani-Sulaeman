package com.example.apkpiw;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText etNama;
    RadioGroup rgTipe;
    RadioButton rbDineIn, rbTakeaway;
    CheckBox cbPedas, cbAlatMakan;
    Button btnPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen
        etNama      = findViewById(R.id.etNama);
        rgTipe      = findViewById(R.id.rgTipe);
        rbDineIn    = findViewById(R.id.rbDineIn);
        rbTakeaway  = findViewById(R.id.rbTakeaway);
        cbPedas     = findViewById(R.id.cbPedas);
        cbAlatMakan = findViewById(R.id.cbAlatMakan);
        btnPesan    = findViewById(R.id.btnPesan);

        btnPesan.setOnClickListener(v -> {
            String nama = etNama.getText().toString().trim();

            // Validasi nama kosong
            if (nama.isEmpty()) {
                etNama.setError("Nama tidak boleh kosong!");
                Toast.makeText(this, "Pesanan gagal!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ambil tipe pesanan
            int selectedId = rgTipe.getCheckedRadioButtonId();
            String tipe = (selectedId == R.id.rbDineIn) ? "Dine In" : "Takeaway";

            // Ambil opsi tambahan
            String opsi = "";
            if (cbPedas.isChecked())     opsi += "• Ekstra Pedas (+Rp 2k)\n";
            if (cbAlatMakan.isChecked()) opsi += "• Minta Alat Makan\n";
            if (opsi.isEmpty())          opsi = "• Tidak ada\n";

            // Buat pesan ringkasan
            String pesan =
                    "Nama    : " + nama + "\n" +
                            "Tipe    : " + tipe + "\n" +
                            "Opsi    :\n" + opsi;

            // Tampilkan AlertDialog konfirmasi
            new AlertDialog.Builder(this)
                    .setTitle("Konfirmasi Pesanan")
                    .setMessage(pesan)
                    .setPositiveButton("PROSES", (dialog, which) -> {
                        Toast.makeText(this, "Pesanan Diproses!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Batal", null)
                    .show();
        });
    }
}