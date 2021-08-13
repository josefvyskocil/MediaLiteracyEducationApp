package cz.cvut.fel.bp.vyskojo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cz.cvut.fel.bp.vyskojo1.activity.LessonsActivity;
import cz.cvut.fel.bp.vyskojo1.database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDB = new MyDatabaseHelper(MainActivity.this);
        insertData();

        setContentView(R.layout.activity_main);

    }

    public void onLectionsClicked(View view) {
        Intent intent = new Intent(MainActivity.this, LessonsActivity.class);
        String tag = view.getTag().toString();
        //TODO přes ty tagy dodělat rozhodování jaké lekce vybrat(obrázky, texty, grafy)
        intent.putExtra("tag", tag);
        startActivity(intent);
    }

    //TODO přidat data z MQtesteru přesunout do data package
    public void insertData(){
        if (myDB.readAllData().getCount() == 0) {
            myDB.addLesson(1, "text", "Kdo jsou „internetoví trollové“? ", "", 0, 2, "Internetoví trollové", "Všichni uživatelé, kteří na internetu vystupují pod falešnou identitou.", "Uživatelé, kteří píšou na internetu urážlivé nebo nesouvisející příspěvky.","Uživatelé, kteří mají rádi fantasy příběhy.","Správci serverů.","");
            myDB.addLesson(1, "text", "O návštěvě Andreje Babiše v Bílém domě bylo publikováno mnoho článků. Česká televize, Extra.cz (bulvární online portál) a Sputniknews (ruská státní zpravodajská agentura) zvolily stejnou fotografii, ale jiné titulky. Poznáte, který vyšel na Extra.cz?", "babis_trump", 1, 2, "Bulvár", "Babiš po přijetí v Bílém domě řekl, že s Trumpem navázal dobrý osobní vztah. ", "ŘEČ TĚLA A PENĚZ: Natěšený Andrej, netrpělivý Donald! Roli sehrálo i tučnější konto. ","Zapojíme se do války na Ukrajině: Okamura rozluštil prohlášení ze setkání Babiše s Trumpem.","","Čtenářům Extra.cz byl určen titulek o řeči těla a tučných kontech. První titulek je z České televize, třetí vydal Sputnik. Stejnou fotku dvou politiků každé médium titulkem tematicky „zarámovalo“ tak, aby zaujalo své typické čtenáře. ");
            myDB.addLesson(1, "text", "Který z následujících titulků nejpravděpodobněji ve čtenářích vzbudí dojem, že jezdit autem je nebezpečné?", "", 0, 2, "Titulek smrti", "Na silnicích v Česku zemřelo loni 571 z 10 miliónů občanů.", "Na silnici loni zemřelo v Česku 571 lidí. Oběťmi byly i nevinné děti.", "Loni byla v ČR pravděpodobnost úmrtí v důsledku dopravní nehody 0,00005396975 %.", "", "Matematicky věty vyjadřují totéž, psychologicky ale působí rozdílně.");
            myDB.addLesson(1, "text", "Jaký je rozdíl mezi novinářskými žánry „zpráva“ a „komentář“?", "", 0, 3, "Zpráva nebo komentář?", "Žádný, obojí poskytují fakta o aktuálním dění.", "Zprávy jsou publikovány v tištěných novinách i na zpravodajských webech, komentáře pouze na internetu.", "Zpráva nesděluje, jaké názory má na danou událost nebo problém její autor, komentář ano. ", "", "");
            myDB.addLesson(1, "text", "Co je to „fake news“?", "", 0, 2, "Fake news", "Anglický výraz pro zprávy v komerčních médiích.", "Nepravdivá nebo částečně nepravdivá zpráva.", "Obecné označení zpráv od neznámého autora.", "", "");
            myDB.addLesson(1, "text", "Co je pro provozovatele webů hlavním důvodem pro umisťování online reklamy na webové stránky? ", "", 0, 2, "Online reklama", "Chtějí doporučit návštěvníkům svého webu ty nejlepší produkty a služby.", "Za umístěnou reklamu získávají peníze. ", "Zaplňují tak prostor, který by byl jinak nevyužitý.", "Internetové vyhledavače stránky s reklamami zobrazují přednostně.", "");
        }
    }

}
