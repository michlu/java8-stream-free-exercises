package pl.klolo.workshops.logic;

import pl.klolo.workshops.domain.*;
import pl.klolo.workshops.domain.Currency;
import pl.klolo.workshops.mock.HoldingMockGenerator;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

class WorkShop {
    /**
     * Lista holdingów wczytana z mocka.
     */
    private final List<Holding> holdings;

    private final Predicate<User> isWoman = null;

    WorkShop() {
        final HoldingMockGenerator holdingMockGenerator = new HoldingMockGenerator();
        holdings = holdingMockGenerator.generate();
    }

    /** 1
     * Metoda zwraca liczbę holdingów w których jest przynajmniej jedna firma.
     */
    long getHoldingsWhereAreCompanies() {
        return -1;
    }

    /** 2
     * Zwraca nazwy wszystkich holdingów pisane z małej litery w formie listy.
     */
    List<String> getHoldingNames() {
        return null;
    }

    /** 3
     * Zwraca nazwy wszystkich holdingów sklejone w jeden string i posortowane.
     * String ma postać: (Coca-Cola, Nestle, Pepsico)
     */
    String getHoldingNamesAsString() {
        return null;
    }

    /** 4
     * Zwraca liczbę firm we wszystkich holdingach.
     */
    long getCompaniesAmount() {
        return -1;
    }

    /** 5
     * Zwraca liczbę wszystkich pracowników we wszystkich firmach.
     */
    long getAllUserAmount() {
        return -1;
    }

    /** 6
     * Zwraca listę wszystkich nazw firm w formie listy. Tworzenie strumienia firm umieść w osobnej metodzie którą
     * później będziesz wykorzystywać.
     */
    List<String> getAllCompaniesNames() {
        return null;
    }

    /** 7
     * Zwraca listę wszystkich firm jako listę, której implementacja to LinkedList. Obiektów nie przepisujemy po zakończeniu
     * działania strumienia.
     */
    LinkedList<String> getAllCompaniesNamesAsLinkedList() {
        return null;
    }

    /** 8
     * Zwraca listę firm jako string gdzie poszczególne firmy są oddzielone od siebie znakiem "+"
     */
    String getAllCompaniesNamesAsString() {
        return null;
    }

    /** 9
     * Zwraca listę firm jako string gdzie poszczególne firmy są oddzielone od siebie znakiem "+".
     * Używamy collect i StringBuilder.
     * <p>
     * UWAGA: Zadanie z gwiazdką. Nie używamy zmiennych.
     */
    String getAllCompaniesNamesAsStringUsingStringBuilder() {
        return null;
    }

    /** 10
     * Zwraca liczbę wszystkich rachunków, użytkowników we wszystkich firmach.
     */
    long getAllUserAccountsAmount() {
        return -1;
    }

    /** 11
     * Zwraca listę wszystkich walut w jakich są rachunki jako string, w którym wartości
     * występują bez powtórzeń i są posortowane oraz odzielone przecinkami.
     */
    String getAllCurrencies() {
        return null;
    }

    /** 12
     * Metoda zwraca analogiczne dane jak getAllCurrencies, jednak na utworzonym zbiorze nie uruchamiaj metody
     * stream, tylko skorzystaj z  Stream.generate. Wspólny kod wynieś do osobnej metody.
     *
     * @see #getAllCurrencies()
     */
    String getAllCurrenciesUsingGenerate() {
        return null;
    }

    /** 13
     * Zwraca liczbę kobiet we wszystkich firmach. Powtarzający się fragment kodu tworzący strumień uzytkowników umieść w osobnej
     * metodzie (na końcu klasy). Predicate określający czy mamy doczynienia z kobietą to pole finalne w klasie (na początku klasy).
     */
    long getWomanAmount() {
        return -1;
    }


    /** 14
     * Przelicza kwotę na rachunku na złotówki za pomocą kursu określonego w enum Currency.
     */
    BigDecimal getAccountAmountInPLN(final Account account) {
        return null;
    }

    /** 15
     * Przelicza kwotę na podanych rachunkach na złotówki za pomocą kursu określonego w enum Currency  i sumuje ją.
     */
    BigDecimal getTotalCashInPLN(final List<Account> accounts) {
        return null;
    }

    /** 16
     * Zwraca imiona użytkowników w formie zbioru, którzy spełniają podany warunek.
     */
    Set<String> getUsersForPredicate(final Predicate<User> userPredicate) {
        return null;
    }

    /** 17
     * Metoda filtruje użytkowników starszych niż podany jako parametr wiek, wyświetla ich na konsoli, odrzuca mężczyzn
     * i zwraca ich imiona w formie listy.
     */
    List<String> getOldWoman(final int age) {
        return null;
    }

    /** 18
     * Dla każdej firmy uruchamia przekazaną metodę.
     */
    void executeForEachCompany(final Consumer<Company> consumer) {
        throw new IllegalArgumentException();
    }

    /** 19
     * Wyszukuje najbogatsza kobietę i zwraca ja. Metoda musi uzwględniać to że rachunki są w różnych walutach.
     */
    Optional<User> getRichestWoman() {
        return null;
    }

    /** 20
     * Zwraca nazwy pierwszych N firm. Kolejność nie ma znaczenia.
     */
    Set<String> getFirstNCompany(final int n) {
        return null;
    }

    /** 21
     * Metoda zwraca jaki rodzaj rachunku jest najpopularniejszy. Stwórz pomocniczą metdę getAccountStream.
     * Jeżeli nie udało się znaleźć najpopularnijeszego rachunku metoda ma wyrzucić wyjątek IllegalStateException.
     * Pierwsza instrukcja metody to return.
     */
    AccountType getMostPopularAccountType() {
        return null;
    }

    /** 22
     * Zwraca pierwszego z brzegu użytkownika dla podanego warunku. W przypadku kiedy nie znajdzie użytkownika wyrzuca wyjątek
     * IllegalArgumentException.
     */
    User getUser(final Predicate<User> predicate) {
        return null;
    }

    /** 23
     * Zwraca mapę firm, gdzie kluczem jest jej nazwa a wartością lista pracowników.
     */
    Map<String, List<User>> getUserPerCompany() {
        return null;
    }


    /** 24
     * Zwraca mapę firm, gdzie kluczem jest jej nazwa a wartością lista pracowników przechowywanych jako string
     * składający się z imienia i nazwiska. Podpowiedź:  Możesz skorzystać z metody entrySet.
     */
    Map<String, List<String>> getUserPerCompanyAsString() {
        return null;
    }

    /** 25
     * Zwraca mapę firm, gdzie kluczem jest jej nazwa a wartością lista pracowników przechowywanych jako obiekty
     * typu T, tworzonych za pomocą przekazanej funkcji.
     */
    <T> Map<String, List<T>> getUserPerCompany(final Function<User, T> converter) {
        return null;
    }

    /** 26
     * Zwraca mapę gdzie kluczem jest flaga mówiąca o tym czy mamy do czynienia z mężczyzną, czy z kobietą.
     * Osoby "innej" płci mają zostać zignorowane. Wartością jest natomiast zbiór nazwisk tych osób.
     */
    Map<Boolean, Set<String>> getUserBySex() {
        return null;
    }

    /** 27
     * Zwraca mapę rachunków, gdzie kluczem jesy numer rachunku, a wartością ten rachunek.
     */
    Map<String, Account> createAccountsMap() {
        return null;
    }

    /** 28
     * Zwraca posortowaną alfabetycznie listę wszystkich imion w postaci Stringa, gdzie imiona oddzielone są spacją i nie zawierają powtórzeń.
     */
    String getUserNames() {
        return null;
    }

    /** 29
     * zwraca zbiór wszystkich użytkowników. Jeżeli jest ich więcej niż 10 to obcina ich ilość do 10.
     */
    Set<User> getUsers() {
        return null;
    }

    /** 30
     * Zapisuje listę numerów rachunków w pliku na dysku, gdzie w każda linijka wygląda następująco:
     * NUMER_RACHUNKU|KWOTA|WALUTA
     * <p>
     * Skorzystaj z strumieni i try-resources.
     */
    void saveAccountsInFile(final String fileName) {
        throw new IllegalArgumentException("not implemented yet");
    }

    /** 31
     * Zwraca użytkownika, który spełnia podany warunek.
     */
    Optional<User> findUser(final Predicate<User> userPredicate) {
        return null;
    }

    /** 32
     * Dla podanego użytkownika zwraca informacje o tym ile ma lat w formie:
     * IMIE NAZWISKO ma lat X. Jeżeli użytkownik nie istnieje to zwraca text: Brak użytkownika.
     * <p>
     * Uwaga: W prawdziwym kodzie nie przekazuj Optionali jako parametrów.
     */
    String getAdultantStatus(final Optional<User> user) {
        return null;
    }

    /** 33
     * Metoda wypisuje na ekranie wszystkich użytkowników (imie, nazwisko) posortowanych od z do a.
     * Zosia Psikuta, Zenon Kucowski, Zenek Jawowy ... Alfred Pasibrzuch, Adam Wojcik
     */
    void showAllUser() {
        throw new IllegalArgumentException("not implemented yet");
    }

    /** 34
     * Zwraca mapę, gdzie kluczem jest typ rachunku a wartością kwota wszystkich środków na rachunkach tego typu przeliczona na złotówki.
     */
    Map<AccountType, BigDecimal> getMoneyOnAccounts() {
        return null;
    }

    /** 35
     * Zwraca sumę kwadratów wieków wszystkich użytkowników.
     */
    int getAgeSquaresSum() {
        return -1;
    }

    /** 36
     * Metoda zwraca N losowych użytkowników (liczba jest stała). Skorzystaj z metody generate. Użytkownicy nie mogą się powtarzać, wszystkie zmienną
     * muszą być final. Jeżeli podano liczbę większą niż liczba użytkowników należy wyrzucić wyjątek (bez zmiany sygnatury metody).
     */
    List<User> getRandomUsers(final int n) {
        return null;
    }

    /**
     * Zwraca strumień wszystkich firm.
     */
    private Stream<Company> getCompanyStream() {
        return null;
    }

    /**
     * Zwraca zbiór walut w jakich są rachunki.
     */
    private Set<Currency> getCurenciesSet() {
        return null;
    }

    /**
     * Tworzy strumień rachunków.
     */
    private Stream<Account> getAccoutStream() {
        return null;
    }

    /**
     * Tworzy strumień użytkowników.
     */
    private Stream<User> getUserStream() {
        return null;
    }

}