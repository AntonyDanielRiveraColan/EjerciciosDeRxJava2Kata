import io.reactivex.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.concurrent.TimeUnit;

class CountriesServiceSolved implements CountriesService {

    @Override
    public Single<String> countryNameInCapitals(Country country) {
        return Single.just(country.name.toUpperCase());
    }

    public Single<Integer> countCountries(List<Country> countries) {
        return Observable.fromIterable(countries).count().map(Long::intValue);
    }

    public Observable<Long> listPopulationOfEachCountry(List<Country> countries) {
        return Observable.fromIterable(countries).map(x -> x.population);
    }

    @Override
    public Observable<String> listNameOfEachCountry(List<Country> countries) {
        return  Observable.fromIterable(countries).map(x -> x.name);
    }

    @Override
    public Observable<Country> listOnly3rdAnd4thCountry(List<Country> countries) {
        return Observable.fromIterable(countries).skip(2).take(2);
    }

    @Override
    public Single<Boolean> isAllCountriesPopulationMoreThanOneMillion(List<Country> countries) {
        return Observable.fromIterable(countries).all(country -> country.population > 1000000);
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillion(List<Country> countries) {
       return Observable.fromIterable(countries).filter(x -> x.population >1000000);
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(final FutureTask<List<Country>> countriesFromNetwork) {

        return Observable.fromFuture(countriesFromNetwork)
            .flatMap(x -> Observable.fromIterable(x))
            .filter(y -> y.population > 1000000);
    }

    @Override
    public Observable<String> getCurrencyUsdIfNotFound(String countryName, List<Country> countries) {
        Country countryDefault = new Country("Senegal","USD",0);
        return Observable.fromIterable(countries)
            .filter(country -> country.name.equals(countryName))
            .defaultIfEmpty(countryDefault)
            .map(country -> country.currency);
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(List<Country> countries) {
        return Observable.fromIterable(countries)
            .reduce(0L,(value, contry) -> value + contry.population).toObservable();
    }

    @Override
    public Single<Map<String, Long>> mapCountriesToNamePopulation(List<Country> countries) {
        return Observable.fromIterable(countries)
            .toMap(x -> x.name, y -> y.population);
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(Observable<Country> countryObservable1,
                                                     Observable<Country> countryObservable2) {
        return null; // put your solution here
    }

    @Override
    public Single<Boolean> areEmittingSameSequences(Observable<Country> countryObservable1,
                                                    Observable<Country> countryObservable2) {
        return null; // put your solution here
    }
}
