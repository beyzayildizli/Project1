
/**
 * @file Proje1
 * @description Bu program, verilen karmaşık bir metni, sözlük yapısını
 * kullanarak anlamlı bir metin haline dönüştürmemizi ve hedef metni
 * oluşturduktan sonra bu metinde, sözlükteki her bir kelimenin kaç defa
 * geçtiğini bulup sözlükteki kelime sırasına göre yazdırmamızı sağlar. Sözlük
 * ve karmaşık metin programın içinde tanımlıdır ve kullanıcıdan istenmez.
 * @assignment 1.Proje
 * @date 5.12.2021
 * @author @beyza.yildizli@stu.fsm.edu.tr
 */
public class IlkProje {

    public static void main(String[] args) {

        String sozluk = "1901 1964 arkansas army beaver corps engineers harvey harveys hope it lake missouri monte ne oklahoma ozark river rogers row rows states the two us united white william a after almost and area as at be boat buildings by can community completely concrete created death due earliest east edge examples financial former from health hills his hotels in is its lake largest levels log mainly managed management mid-1930s multi-story normal not of off on one only open operated owned part planned property ramp referred remainder remaining resort seen serves severely shortly sold state still structure style submerged success that the theorist time to tower town valley vandalized was were world writer";
        String karmasikMetin = "onteM Ne si a merfor health esortr and nedplan unitycomm in het sU atest fo rkansasA nope omfr 9011 to the d-1930smi It asw owned nda by liamWil eHop arveyH a ncialfina ttheoris dan riterw ni eth kOzar llshi fo the iteWh iverR leyval east of sRoger no the edge of averBe eLak woT of sit hotels Missouri Row nda maOklaho Row ewer eth estlarg log ngsbuildi in eth rldwo at the etim and aOklahom tower si neo fo het rliestea xamplese of a storymulti- ncreteco restructu The ortres was otn a alfinanci uccesss edu ni rtpa ot entmanagem ylest dan yshortl eraft ish thdea the typroper swa dsol ffo heT remainder fo het esortr and town asw stalmo ompletelyc mergedsub rafte erBeav Lake was atedcre in 1964 heT erelysev zedvandali Oklahoma wRo werto is het yonl emainingr ructurest atth anc eb seen at alnorm kela lsleve heT area on eth edge of averBe Lake llsti erredref ot sa Monte eN edown and gedmana by het tedUni States yArm orpsC of sEngineer esserv nlymai as a tboa ampr";

        //Stringleri kelime kelime arraylere dolduralım:
        String[] karmasikMetinList = metniDiziyeCevir(hepsiniKucukYap(karmasikMetin));
        String[] sozlukListTekrarli = metniDiziyeCevir(hepsiniKucukYap(sozluk));

        //sozlukListTekrarlida tekrar eden kelimeler var. 
        //O yüzden sözlük için tekrar eden kelimelerin olmadığı yeni bir liste oluşturdum:
        String[] sozlukList = tekrarsizArray(sozlukListTekrarli);

        //Karmaşık metni, sözlük yapısını kullanarak anlamlı bir metin haline dönüştürelim:
        String duzeltilmisMetin = "";
        //a= listenin kaçıncı elemanı
        //b= sözlüğün kaçıncı elemanı
        for (int a = 0; a < karmasikMetinList.length; a++) {
            for (int b = 0; b < sozlukList.length; b++) {
                for (int n = 0; n < karmasikMetinList[a].length(); n++) {
                    if (sagaKaydir(karmasikMetinList[a], n).equals(sozlukList[b])) {
                        duzeltilmisMetin += sozlukList[b] + " ";
                    }
                }
            }
        }

        //anlamlı metnin çıktısını alalım:
        System.out.println("Anlamlı metin: " + duzeltilmisMetin);

        //anlamlı metni de kelime kelime bir arraye dolduralım:
        String[] duzeltilmisMetinList = metniDiziyeCevir(duzeltilmisMetin);

        //Kelime Tekrarları Sayacı:
        int kelimeSayisi = (duzeltilmisMetinList.length) - 1; //(duzeltilmisMetinList.length)-1 dememin sebebi = duzeltilmisMetin'de her kelimeden sonra boşluk bıraktığımız için sonrasında kelime olmasa da en sonda fazladan bir boşluk var. Bu da duzeltilmisMetinList'in, kelime sayısının 1 fazlası indexe sahip olmasına sebep oluyor.
        int[] tekrarSayaci = new int[sozlukListTekrarli.length];

        for (int a = 0, b = 0; a < sozlukListTekrarli.length; a++) {
            while (b < kelimeSayisi) {
                if (sozlukListTekrarli[a].equals(duzeltilmisMetinList[b])) {
                    tekrarSayaci[a]++;
                }
                b++;
            }
            b = 0;
        }

        //Sayacı bastıralım:
        System.out.print("Kelime Tekrarları: ");
        for (int i = 0; i < sozlukListTekrarli.length; i++) {
            System.out.print(tekrarSayaci[i] + " ");
        }

    }

    public static String hepsiniKucukYap(String kelime) {
        String kelime2 = "";
        for (int i = 0; i < kelime.length(); i++) {
            if ((int) kelime.charAt(i) <= 90 && (int) kelime.charAt(i) >= 65) {
                int fark = 'a' - 'A';
                kelime2 += (char) (kelime.charAt(i) + fark);
            } else {
                kelime2 += kelime.charAt(i);
            }
        }
        return kelime2;
    }

    public static String sagaKaydir(String word, int n) {
        if (n != 0) {
            String yeniKelime = "";
            int tekrar = 0;
            do {
                yeniKelime += word.charAt(word.length() - 1);
                for (int i = 0; i < (word.length() - 1); i++) {
                    yeniKelime += word.charAt(i);
                }
                word = yeniKelime;
                yeniKelime = "";
                tekrar++;
            } while (tekrar < n);
        }
        return word;
    }

    public static String[] metniDiziyeCevir(String metin) {
        int KelimeSayisi;  //metindeki kelime sayısı
        KelimeSayisi = 1;
        for (int i = 0; i < metin.length(); i++) {
            if (metin.charAt(i) == ' ') {
                KelimeSayisi++;
            }
        }

        String[] metinList = new String[KelimeSayisi];

        //null ları sildim:
        for (int i = 0; i < KelimeSayisi; i++) {
            metinList[i] = "";
        }

        //metni yeni bir diziye kelime kelime yerleştirdim:
        //a= harf kontrol
        //b= metinList in kaçıncı indexi
        for (int a = 0, b = 0; a < metin.length(); a++) {
            if (metin.charAt(a) == ' ') {
                b++;
            } else {
                metinList[b] = metinList[b] + metin.charAt(a);
            }
        }
        return metinList;
    }

    public static String[] tekrarsizArray(String dizi[]) {
        String[] diziTekrarsiz = new String[dizi.length];
        for (int i = 0; i < dizi.length; i++) {
            diziTekrarsiz[i] = dizi[i];
        }

        //tekrar eden kelimeler yerine boşluk koy.
        for (int a = 0, b = 0; a < dizi.length; a++) {
            while (b < diziTekrarsiz.length) {
                if (dizi[a].equals(dizi[b])) {
                    diziTekrarsiz[b] = " ";
                }
                diziTekrarsiz[a] = dizi[a];
                b++;
            }
            b = 0;
        }

        //boşlukları say
        int bosluksayisi = 0;
        for (int i = 0; i < diziTekrarsiz.length; i++) {
            if (diziTekrarsiz[i] == " ") {
                bosluksayisi++;
            }
        }

        //diziTekrarsizBosluksuz adlı dizi oluşturduk ve önceki dizideki (boşluklar hariç) indexlerle doldurduk.
        String[] diziTekrarsizBosluksuz = new String[diziTekrarsiz.length - bosluksayisi];
        for (int a = 0, b = 0; a < diziTekrarsiz.length; a++) {
            if (diziTekrarsiz[a] != " ") {
                diziTekrarsizBosluksuz[b] = diziTekrarsiz[a];
                b++;
            }
        }
        return diziTekrarsizBosluksuz;
    }
    
}