PROIECT ETAPA 1 POO - PAUNOIU IONUT-EDUARD - GRUPA 323AC

Pentru inceput, am creat clasele GameInput si GameInputLoader care fac parsarea 
din fisierul de intrare. Initial, voiam sa creez si o clasa GamePlay in care sa
se desfasoare jocul, insa am lasat actiunea sa se deruleze in clasa Main. 
Outputul se genereaza de asemenea in Main.

Clasa de baza Hero contine trasaturile unui erou (HP, XP, level, etc) dar si 
metode specifice pentru aplicarea damage ului activ, pasiv, pentru a imobiliza
eroii, etc. Am cateva metode neimplementate(prepareHero, battles) ce urmeaza sa
fie suprascrise in clasele ce extind Hero. In plus, in hero se face si mutarea 
eroilor pe harta, prin metodele moveUp/Down...
Pentru mai multe detalii, see javadoc.

In Modifier creez practic modificatorii pentru fiecare erou. Ei vor fi instantiati
in fiecare clasa care extinde Hero, fiind specifici unui anumit tip de jucator.

Nu am ales sa fac clase separate pentru fiecare abilitate(asta era ideea initiala),
deoarece am considerat ca pot calcula damage-ul mai simplu in fiecare clasa. 

Legat de mecanismul jocului, in fiecare runda mut jucatorii pe harta, aplic 
damage-ul pasiv daca exista. Dupa, parcurg jucatorii, iar daca se afla pe aceeasi
pozitie pe harta acestia se ataca reciproc. Rogue ataca primul
deoarece trebuie sa dea Critical Hit prima data(atuni cand se afla pe woods).

Nu am implementat Double Dispatch. 



