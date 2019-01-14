package pl.klolo.workshops.logic;

import pl.klolo.workshops.domain.*;
import pl.klolo.workshops.domain.Currency;
import pl.klolo.workshops.mock.HoldingMockGenerator;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class WorkShop {
    /**
     * Lista holdingów wczytana z mocka.
     */
    private final List<Holding> holdings;

    private final Predicate<User> isWoman = u -> u.getSex().equals(Sex.WOMAN);

    WorkShop() {
        final HoldingMockGenerator holdingMockGenerator = new HoldingMockGenerator();
        holdings = holdingMockGenerator.generate();
    }

    /** 1
     * Metoda zwraca liczbę holdingów w których jest przynajmniej jedna firma.
     */
    long getHoldingsWhereAreCompanies() {
        return holdings.stream()
                .filter(e -> e.getCompanies().size() >= 1)
                .count();
    }

    /** 2
     * Zwraca nazwy wszystkich holdingów pisane z małej litery w formie listy.
     */
    List<String> getHoldingNames() {
        return holdings.stream()
                .map(h -> h.getName().toLowerCase())
                .collect(Collectors.toList());
    }

    /** 3
     * Zwraca nazwy wszystkich holdingów sklejone w jeden string i posortowane.
     * String ma postać: (Coca-Cola, Nestle, Pepsico)
     */
    String getHoldingNamesAsString() {
        return holdings.stream()
                .map(Holding::getName)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(", ", "(", ")"));
    }

    /** 4
     * Zwraca liczbę firm we wszystkich holdingach.
     */
    long getCompaniesAmount() {
        return holdings.stream()
                .mapToLong(h -> h.getCompanies().size())
                .sum();
    }

    /** 5
     * Zwraca liczbę wszystkich pracowników we wszystkich firmach.
     */
    long getAllUserAmount() {
        return holdings.stream()
                .map(Holding::getCompanies)
                .flatMap(List::stream)
                .map(Company::getUsers)
                .flatMap(List::stream)
                .count();
    }

    /** 6
     * Zwraca listę wszystkich nazw firm w formie listy. Tworzenie strumienia firm umieść w osobnej metodzie którą
     * później będziesz wykorzystywać.
     */
    List<String> getAllCompaniesNames() {
        return getCompanyStream()
                .map(Company::getName)
                .collect(Collectors.toList());
    }

    /** 7
     * Zwraca listę wszystkich firm jako listę, której implementacja to LinkedList. Obiektów nie przepisujemy po zakończeniu
     * działania strumienia.
     */
    LinkedList<String> getAllCompaniesNamesAsLinkedList() {
        return getCompanyStream()
                .map(c -> c.getName())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /** 8
     * Zwraca listę firm jako string gdzie poszczególne firmy są oddzielone od siebie znakiem "+"
     */
    String getAllCompaniesNamesAsString() {
        return getCompanyStream()
                .map(c -> c.getName())
                .collect(Collectors.joining("+"));
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
        return getCompanyStream()
                .map(Company::getUsers)
                .flatMap(List::stream)
                .map(User::getAccounts)
                .flatMap(List::stream)
                .count();
    }

    /** 11
     * Zwraca listę wszystkich walut w jakich są rachunki jako string, w którym wartości
     * występują bez powtórzeń i są posortowane oraz odzielone przecinkami.
     */
    String getAllCurrencies() {
        return getCompanyStream()
                .map(Company::getUsers)
                .flatMap(List::stream)
                .map(User::getAccounts)
                .flatMap(List::stream)
                .map(Account::getCurrency)
                .map(Currency::toString)
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(", "));
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
        return getUserStream()
                .filter(isWoman)
                .count();
    }


    /** 14
     * Przelicza kwotę na rachunku na złotówki za pomocą kursu określonego w enum Currency.
     */
    BigDecimal getAccountAmountInPLN(final Account account) {
        if (account.getCurrency().equals(Currency.USD)) {
            return account.getAmount().multiply(new BigDecimal(Float.toString(Currency.USD.rate)));
        } else if (account.getCurrency().equals(Currency.CHF)) {
            return account.getAmount().multiply(new BigDecimal(Float.toString(Currency.CHF.rate)));
        } else if (account.getCurrency().equals(Currency.EUR)) {
            return account.getAmount().multiply(new BigDecimal(Float.toString(Currency.EUR.rate)));
        } else {
            return account.getAmount(); // nie najlepsze rozwiazanie
        }
    }

    /** 15
     * Przelicza kwotę na podanych rachunkach na złotówki za pomocą kursu określonego w enum Currency  i sumuje ją.
     */
    BigDecimal getTotalCashInPLN(final List<Account> accounts) {
        return accounts
                .stream()
                .map(a -> this.getAccountAmountInPLN(a))
                .reduce(BigDecimal::add)
                .get();
    }

    /** 16
     * Zwraca imiona użytkowników w formie zbioru, którzy spełniają podany warunek.
     */
    Set<String> getUsersForPredicate(final Predicate<User> userPredicate) {
        return getUserStream()
                .filter(userPredicate)
                .map(User::getFirstName)
                .collect(Collectors.toSet());
    }

    /** 17
     * Metoda filtruje użytkowników starszych niż podany jako parametr wiek, wyświetla ich na konsoli, odrzuca mężczyzn
     * i zwraca ich imiona w formie listy.
     */
    List<String> getOldWoman(final int age) {
        return getUserStream()
                .filter(u -> u.getAge() > age)
                .peek(System.out::println)
                .filter(isWoman)
                .map(User::getFirstName)
                .collect(Collectors.toList());
    }

    /** 18
     * Dla każdej firmy uruchamia przekazaną metodę.
     */
    void executeForEachCompany(final Consumer<Company> consumer) {
        getCompanyStream()
                .forEach(consumer);
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
        return getCompanyStream()
                .limit(n)
                .map(Company::getName)
                .collect(Collectors.toCollection(HashSet::new));
    }

    /** 21
     * Metoda zwraca jaki rodzaj rachunku jest najpopularniejszy. Stwórz pomocniczą metdę getAccountStream.
     * Jeżeli nie udało się znaleźć najpopularnijeszego rachunku metoda ma wyrzucić wyjątek IllegalStateException.
     * Pierwsza instrukcja metody to return.
     */
    AccountType getMostPopularAccountType() {
        return getAccoutStream()
                .map(Account::getType)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(IllegalStateException::new);
    }

    /** 22
     * Zwraca pierwszego z brzegu użytkownika dla podanego warunku. W przypadku kiedy nie znajdzie użytkownika wyrzuca wyjątek
     * IllegalArgumentException.
     */
    User getUser(final Predicate<User> predicate) {
        return getUserStream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    /** 23
     * Zwraca mapę firm, gdzie kluczem jest jej nazwa a wartością lista pracowników.
     */
    Map<String, List<User>> getUserPerCompany() {
        return getCompanyStream()
                .collect(Collectors.toMap(Company::getName, Company::getUsers));
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
        return getUserStream()
                .map(User::getFirstName)
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.joining(" "));
    }

    /** 29
     * zwraca zbiór wszystkich użytkowników. Jeżeli jest ich więcej niż 10 to obcina ich ilość do 10.
     */
    Set<User> getUsers() {
        return getUserStream()
                .limit(10)
                .collect(Collectors.toSet());
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
        return getUserStream()
                .filter(userPredicate)
                .findAny();
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
        return holdings.stream()
                .map(Holding::getCompanies)
                .flatMap(List::stream);
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
        return holdings.stream()
                .map(Holding::getCompanies)
                .flatMap(List::stream)
                .map(Company::getUsers)
                .flatMap(List::stream)
                .map(User::getAccounts)
                .flatMap(List::stream);
    }

    /**
     * Tworzy strumień użytkowników.
     */
    private Stream<User> getUserStream() {
        return holdings.stream()
                .map(Holding::getCompanies)
                .flatMap(List::stream)
                .map(Company::getUsers)
                .flatMap(List::stream);
    }

}