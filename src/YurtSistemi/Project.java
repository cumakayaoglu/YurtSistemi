package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Project extends JFrame implements ActionListener {
    String userRole; // Kullanıcı rolü
    Project(String role){
        this.userRole = role; // Role'yi al
        setSize(1540,850);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/yurt.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500,750,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        JMenuBar mb = new JMenuBar();

        // "Yeni Kayıt" menüsünü sadece memura göstermek
        if (userRole.equals("memur")) {
            JMenu yeniKayit = new JMenu("Yeni Kayıt");
            mb.add(yeniKayit);
            JMenuItem yeniOgrenci = new JMenuItem("Yeni Öğrenci Kaydı");
            yeniOgrenci.addActionListener(this);
            yeniKayit.add(yeniOgrenci);
            JMenuItem yeniCalisan = new JMenuItem("Yeni Çalışan Kaydı");
            yeniCalisan.addActionListener(this);
            yeniKayit.add(yeniCalisan);
        }

        // Detaylar menüsü
        JMenu detaylar = new JMenu("Detaylar");
        mb.add(detaylar);

        // Memurlar için tüm detaylar
        if (userRole.equals("memur")) {
            JMenuItem ogrenciler = new JMenuItem("Öğrenci Detayları");
            ogrenciler.addActionListener(this);
            detaylar.add(ogrenciler);

            JMenuItem calisanlar = new JMenuItem("Çalışan Detayları");
            calisanlar.addActionListener(this);
            detaylar.add(calisanlar);
        }
        // Öğrenciler sadece kendi detaylarını görebilir
         if (userRole.equals("ogrenci")) {
           new ogrenciEkran();
        }



        if (userRole.equals("memur")) {
            JMenu izin = new JMenu("İzin İşlemleri");
            mb.add(izin);
            JMenuItem ogrenciIzin = new JMenuItem("Öğrenci İzin İşlemleri");
            ogrenciIzin.addActionListener(this);
            izin.add(ogrenciIzin);
            JMenuItem calisanIzin = new JMenuItem("Çalışan İzin İşlemleri");
            calisanIzin.addActionListener(this);
            izin.add(calisanIzin);
        }




        JMenu izinKayitlar = new JMenu("İzin Kayıtları");
        mb.add(izinKayitlar);

        if (userRole.equals("memur")) {

            JMenuItem izinOgrenci = new JMenuItem("Öğrenci İzin Kaydı");
            izinOgrenci.addActionListener(this);
            izinKayitlar.add(izinOgrenci);
            JMenuItem izinCalisan = new JMenuItem("Çalışan İzin Kaydı");
            izinCalisan.addActionListener(this);
            izinKayitlar.add(izinCalisan);
        }
        // Öğrenciler sadece kendi detaylarını görebilir
        /* if (userRole.equals("ogrenci")) {
            JMenuItem izinOgrenci = new JMenuItem("Öğrenci İzin Kaydı");
            izinOgrenci.addActionListener(this);
            izinKayitlar.add(izinOgrenci);

        */




        //disiplin işlemleri
        JMenu disiplinIslem = new JMenu("Disiplin İşlemleri");
        mb.add(disiplinIslem);

        if (userRole.equals("memur")) {
            JMenuItem disiplinOgrenci = new JMenuItem("Öğrenci Disiplin İşlemleri");
            disiplinOgrenci.addActionListener(this);
            disiplinIslem.add(disiplinOgrenci);
            JMenuItem disiplinKayit = new JMenuItem("Öğrenci Disiplin Kayıtları");
            disiplinKayit.addActionListener(this);
            disiplinIslem.add(disiplinKayit);
        }
        // Öğrenciler sadece kendi detaylarını görebilir
        else if (userRole.equals("ogrenci")) {
            JMenuItem disiplinKayit = new JMenuItem("Öğrenci Disiplin Kayıtları");
            disiplinKayit.addActionListener(this);
            disiplinIslem.add(disiplinKayit);

        }




        if (userRole.equals("memur")) {
            // update işlemleri
            JMenu updateIslem = new JMenu("Güncelleme İşlemleri");
            mb.add(updateIslem);
            JMenuItem updateOgrenci = new JMenuItem("Öğrenci Güncelleme İşlemleri");
            updateOgrenci.addActionListener(this);
            updateIslem.add(updateOgrenci);
            JMenuItem updateKayit = new JMenuItem("Çalışan Güncelleme İşlemleri");
            updateKayit.addActionListener(this);
            updateIslem.add(updateKayit);
        }


        //fatura işlmeleri



        if (userRole.equals("memur")) {
            //silme işlemleri
            JMenu silmeIslem = new JMenu("Kayıt Silme İşlemleri");
            mb.add(silmeIslem);
            JMenuItem ogrenciSilme = new JMenuItem("Öğrenci Kayıt Silme İşlemleri");
            ogrenciSilme.addActionListener(this);
            silmeIslem.add(ogrenciSilme);
            JMenuItem calisanSilme = new JMenuItem("Çalışan Kayıt Silme İşlemleri");
            calisanSilme.addActionListener(this);
            silmeIslem.add(calisanSilme);
        }



        if (userRole.equals("memur")) {
            JMenu faturaIslem = new JMenu("Fatura İşlemleri");
            mb.add(faturaIslem);
            JMenuItem faturaKayit = new JMenuItem("Fatura Kayıtları");
            faturaKayit.addActionListener(this);
            faturaIslem.add(faturaKayit);
            JMenuItem faturaOgrenci = new JMenuItem("Öğrenci Fatura İşlemleri");
            faturaOgrenci.addActionListener(this);
            faturaIslem.add(faturaOgrenci);
        }
        // Öğrenciler sadece kendi detaylarını görebilir
        /* if (userRole.equals("ogrenci")) {
            JMenuItem faturaKayit = new JMenuItem("Fatura Kayıtları");
            faturaKayit.addActionListener(this);
            faturaIslem.add(faturaKayit);
            JMenuItem faturaOgrenci = new JMenuItem("Öğrenci Fatura İşlemleri");
            faturaOgrenci.addActionListener(this);
            faturaIslem.add(faturaOgrenci);


        }*/
        //exit
        JMenu ex = new JMenu("Çıkış");
        mb.add(ex);
        JMenuItem exit = new JMenuItem("Çıkış");
        exit.addActionListener(this);
        ex.add(exit);


        setJMenuBar(mb);

        setVisible(true);


    }

    public void main(String[] args) {
        new Project(userRole);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if(msg.equals("Çıkış")){
            setVisible(false);
        }else if (msg.equals("Yeni Çalışan Kaydı")) {
            new AddCalisan();
        } else if (msg.equals("Yeni Öğrenci Kaydı")) {
            new AddStudent();
        }else if (msg.equals("Çalışan Detayları")) {
            new CalisanDetaylari();
        } else if (msg.equals("Öğrenci Detayları")) {
            new OgrenciDetaylari();
        } else if (msg.equals("Çalışan İzin İşlemleri")) {
            new IzinCalisan();
        } else if (msg.equals("Öğrenci İzin İşlemleri")) {
            new IzinOgrenci();
        } else if (msg.equals("Çalışan İzin Kaydı")) {
            new IzinKayitCalisan();
        } else if (msg.equals("Öğrenci İzin Kaydı")) {
            new IzinKayitOgrenci();
        } else if (msg.equals("Öğrenci Güncelleme İşlemleri")) {
            new GuncellemeOgrenci();
        }else if (msg.equals("Çalışan Güncelleme İşlemleri")) {
            new GuncellemeCalisan();
        }else if (msg.equals("Öğrenci Disiplin İşlemleri")) {
            new DisiplinIslem();
        }else if (msg.equals("Öğrenci Disiplin Kayıtları")) {
            new DisiplinKayit();
        }else if (msg.equals("Fatura Kayıtları")) {
            new fatura();
        }else if (msg.equals("Öğrenci Fatura İşlemleri")) {
            new faturaIslem();
        }else if (msg.equals("Öğrenci Kayıt Silme İşlemleri")) {
            new SilmeOgrenci();
        }else if (msg.equals("Çalışan Kayıt Silme İşlemleri")) {
            new SilmeCalisan();
        }

    }
}
