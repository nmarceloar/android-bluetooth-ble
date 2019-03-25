package apps.my.p2017;

import android.app.FragmentManager;

import junit.framework.Assert;

import org.junit.Test;

import java.security.cert.PKIXRevocationChecker;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.ToLongFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;


interface HttpClient {

    public void get(String url);

    public void post(String url, Map<String, ?> data);

}

public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {


        System.out.println(
                String.format("%s %s",
                        DateUtils.date(), new Date(DateUtils.date()))

        );


    }

    @Test
    public void calculatesDaysBetween() throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date d1 = dateFormat.parse("1/01/1984");
        Date d2 = dateFormat.parse("1/01/1984");

        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(d1.getTime());

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(d2.getTime());

        long x = DateUtils.daysBetween(c1, c2);
        System.out.println(x);

        c1.add(Calendar.DATE, 0);


        System.out.println(new Date(c1.getTimeInMillis()));


    }

    @Test
    public void mergeOperator() throws Exception {

        Observable.merge(
                Observable.just("marcelo", "maximiliano", "nelson"),
                Observable.just("racing", "club", "de", "avellaneda")
        )
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Completed")
                );


    }

    @Test
    public void zipOperator() throws Exception {

        Observable.zip(
                Observable.just(1, 2),
                Observable.just("uno", "dos"),
                (idx, data) -> String.format("Item %s is in position %d", data, idx)
        )
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        System.out::println
                );


    }

    @Test
    public void reduceOperator() throws Exception {


    }

    @Test
    public void bufferOperator() throws Exception {

        final Calendar c = Calendar.getInstance();

        Observable
                .range(1, 10)
                .map(date -> Randoms.randomIntBetween(1, 10));


    }

    @Test
    public void doWorksLikeTap() throws InterruptedException {

        Observable<Integer> obs1 = Observable.range(1, 10);
        Observable<Integer> obs2 = Observable.range(1, 10);


    }


    @Test
    public void regex() throws Exception {

        Pattern p = Pattern.compile(".*\\s(([0-9]{1,2}(,[0-9]{1,2})*)\\skm\\/h).*", Pattern.CASE_INSENSITIVE);
        Pattern p2 = Pattern.compile(".*\\s([0-9]{1,2}).*");

        String pattern = ".*\\s().*";

        Matcher matcher = p.matcher("Ciclismo, 22.5-25.6 km/h, carrera o recreativo, rápido, esfuerzo intens");

        System.out.println(matcher.groupCount());

        System.out.println(matcher.group(3));


    }
}