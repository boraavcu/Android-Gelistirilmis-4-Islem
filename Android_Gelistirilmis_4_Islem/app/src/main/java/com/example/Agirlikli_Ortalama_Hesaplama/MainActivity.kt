package com.example.Agirlikli_Ortalama_Hesaplama
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
// Bora Avcu 2021707008 DEÜ İMYO Bilgisayar Programcılığı 2.Sınıf Örgün Öğretim
class MainActivity : AppCompatActivity() {
    // Değişkenlerin Hangi Nesneleri Temsil Ettiği Tanımlandı.
    private lateinit var BirinciSayi: EditText
    private lateinit var İkinciSayi: EditText
    private lateinit var ToplamaBtn: Button
    private lateinit var CikarmaBtn: Button
    private lateinit var CarpmaBtn: Button
    private lateinit var BolmeBtn: Button
    private lateinit var TemizleBtn: Button
    private lateinit var RadioGrubu: RadioGroup
    private lateinit var SonucTxt: TextView


    @SuppressLint("SetTextI18n")
    private fun Hesaplama(İşlem: String) {

        val ilkSayi = BirinciSayi.text.toString()
        val ikinciSayi = İkinciSayi.text.toString()

        // Sayı Giriş Yerlerinden Her Hangi Biri veya İkisinin Boş Olması Durumunda Uyarı Toastı Verilecektir.
        if (ilkSayi.isBlank() || ikinciSayi.isBlank()) {
            KlavyeKapat()
            Toast.makeText(this, "Lütfen Tüm Sayıları Girin", Toast.LENGTH_SHORT).show()
            return
        }
        // Sayılar Double Türüne Dönüştürülür Eğer Geçersiz Bir Değer Girilirse İlgili EditText Null Değeri Alır.
        val ilk = ilkSayi.toDoubleOrNull()
        val ikinci = ikinciSayi.toDoubleOrNull()
        // Null Değeri Almış Olan EditText Olursa Aşağıdaki Hata Mesajı Gösterilir.
        if (ilk == null || ikinci == null) {
            KlavyeKapat()
            Toast.makeText(this, "Geçersiz sayılar", Toast.LENGTH_SHORT).show()
            return }

        //Fonksiyonda Belirtilen İşlem Parametresine Göre Hesaplamalar Yapılır.
        when (İşlem) {
            "+" -> {
                // Toplama İşleminde İlk Radio Button Seçildiğinde veya Seçilmediğinde Toplanma Sırasını Belirttim.
                val result = if (RadioGrubu.checkedRadioButtonId == R.id.radiobtn1) ilk + ikinci else ikinci + ilk
                SonucTxt.text = "Sonuç: $result"
            }
            "-" -> {
                // Çıkarma İşleminde İlk Radio Button Seçildiğinde veya Seçilmediğinde Çıkarma Sırasını Belirttim.
                val result = if (RadioGrubu.checkedRadioButtonId == R.id.radiobtn1) ilk - ikinci else ikinci - ilk
                SonucTxt.text = "Sonuç: $result"
            }
            "*" -> {
                // Çarpma İşleminde İlk Radio Button Seçildiğinde veya Seçilmediğinde Çarpma Sırasını Belirttim.
                val result = ilk * ikinci
                SonucTxt.text = "Sonuç: $result"
            }


            "/" -> {
                // Bölme İşleminde Yine Radio Seçimine Göre İşlemler Belirlenmesin Sağladım.
                val isRadioBtn1Selected = RadioGrubu.checkedRadioButtonId == R.id.radiobtn1

                if (isRadioBtn1Selected) {
                    // " 1 --> 2 " Radio Seçildiğinde 1. Sayı "0" ve 2.Sayı 0'dan Farklı İse Sonuç:0 Yazdırılacak.
                    if (ilk == 0.0 && ikinci != 0.0) {
                        SonucTxt.text = "Sonuç: 0.0"
                        return
                    }
                    // " 1 --> 2 " Radio Seçildiğinde 1. Sayı 0'dan Farklı  ve 2.Sayı "0" İse "0'a Bölme Tanımsızdır" Toast Mesajı Gönderilecek.
                    if (ilk != 0.0 && ikinci == 0.0) {
                        KlavyeKapat()
                        Toast.makeText(this, "0'a Bölme Tanımsızdır", Toast.LENGTH_SHORT).show()
                        return
                    }
                    // " 1 --> 2 " Radio Seçildiğinde Hem 1. Sayı Hem de 2. Sayı  0'a Eşit İse "Tanımsız İşlem 0 / 0" Toast Mesajı Gönderilecek.
                    if (ilk == 0.0 || ikinci == 0.0) {
                        KlavyeKapat()
                        Toast.makeText(this, "Tanımsız İşlem 0 / 0", Toast.LENGTH_SHORT).show()
                        return
                    }
                } else {
                    // " 2 --> 1 " Radio Seçildiğinde 2. Sayı "0" ve 1.Sayı 0'dan Farklı İse Sonuç:0 Yazdırılacak.
                    if (ikinci == 0.0 && ilk != 0.0) {
                        SonucTxt.text = "Sonuç: 0.0"
                        return
                    }
                    // " 2 --> 1 " Radio Seçildiğinde  2.Sayı 0'dan Farklı ve 1.Sayı 0'a Eşit İse "0'a Bölme Tanımsızdır" Toast Mesajı Gönderilecek.
                    if (ikinci != 0.0 && ilk == 0.0) {
                        KlavyeKapat()
                        Toast.makeText(this, "0'a Bölme Tanımsızdır", Toast.LENGTH_SHORT).show()
                        return
                    }

                    // " 2 --> 1 " Radio Seçildiğinde Hem 2. Sayı Hem de 1. Sayı 0'a Eşit İse "Tanımsız İşlem 0 / 0" Toast Mesajı Gönderilecek.
                    if (ikinci == 0.0 || ilk == 0.0) {
                        KlavyeKapat()
                        Toast.makeText(this, "Tanımsız İşlem 0 / 0", Toast.LENGTH_SHORT).show()
                        return
                    }
                }

                // Yukarıdaki Durumlardan Farklı Sayı Girişleri Var İse Radio Seçimine Göre Aşağıdaki İşlemler Yapılır ve Sonuç Yazdırılır.
                val result = if (isRadioBtn1Selected) ilk / ikinci else ikinci / ilk
                SonucTxt.text = "Sonuç: $result"
            }
        }
    }

    // Temizle Buttonu İçin Yazılmış "Temizle" Fonksiyonu.
    private fun Temizle() {
        // Sayı Giriş Editlerini ve Sonuç Textini Temizler.
        BirinciSayi.text.clear()
        İkinciSayi.text.clear()
        SonucTxt.text = ""
    }

    // Hata Mesajlarını Görmek İçin Klavyeyi Kapatmak İçin Bir "KlavyeKapat" Adında Fonksiyon Oluşturdum.
    private fun KlavyeKapat() {
        // Klavye Girişini Yönetmek İçin "InputMethodManager" Nesnesi Oluşturdum.
        val Kapat = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        // Etkin Olan Pencereden Klavyeyi Kapatır.
        Kapat.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Sayıların Girildiği ; Editleri, Buttonlari, Radioları ve Sonuç Bölümünü tanımladım.
        BirinciSayi = findViewById(R.id.ilksayiedit)
        İkinciSayi = findViewById(R.id.ikincisayiedit)
        ToplamaBtn = findViewById(R.id.toplamabtn)
        CikarmaBtn = findViewById(R.id.cikarmabtn)
        CarpmaBtn = findViewById(R.id.carpmabtn)
        BolmeBtn = findViewById(R.id.bolmebtn)
        TemizleBtn = findViewById(R.id.temizlebtn)
        RadioGrubu = findViewById(R.id.Radiolar)
        SonucTxt = findViewById(R.id.sonuctxt)
        RadioGrubu.check(R.id.radiobtn1)  // Uygulama Başlatılırken İlk Radio Button Yani " 1 --> 2 " Seçili Olarak Gelmesini Sağladım

        //Toplama Buttonu Tıklanıldığında Hesaplama Fonksiyonundan "+" Parametresi Çağrılacak.
        ToplamaBtn.setOnClickListener {
            Hesaplama("+")
        }
        //Çıkarma Buttonu Tıklanıldığında Hesaplama Fonksiyonundan "-" Parametresi Çağrılacak.
        CikarmaBtn.setOnClickListener {
            Hesaplama("-")
        }
        //Çarpma Buttonu Tıklanıldığında Hesaplama Fonksiyonundan "*" Parametresi Çağrılacak.
        CarpmaBtn.setOnClickListener {
            Hesaplama("*")
        }
        //Bölme Buttonu Tıklanıldığında Hesaplama Fonksiyonundan “/" Parametresi Çağrılacak.
        BolmeBtn.setOnClickListener {
            Hesaplama("/")
        }
        //Temizle Buttonu Tıklanıldığında Temizle Fonksiyonu Çağrılacak.
        TemizleBtn.setOnClickListener {
            Temizle()
        }

    }
}
// Bora Avcu 2021707008 DEÜ İMYO Bilgisayar Programcılığı 2.Sınıf Örgün Öğretim
