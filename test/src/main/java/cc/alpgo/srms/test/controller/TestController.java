package cc.alpgo.srms.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping("flux/create")
    public Flux testFluxCreate() {
        System.out.println("Test Flux create");
        // Flux.generate() only can call next() once
        // Flux.create() can call next() many times
        return Flux.create(sink -> {
            sink.next(Flux.just("Hello", "World").subscribe(System.out::println));
            System.out.println("-----------------------");
            sink.next(Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println));
            System.out.println("-----------------------");
            sink.next(Flux.empty().subscribe(System.out::println));
            System.out.println("-----------------------");
            sink.next(Flux.range(1, 5).subscribe(System.out::println));
            System.out.println("-----------------------");
            sink.complete();
        });
    }

    @GetMapping("mono/create")
    public Mono testMonoCreate() {
        System.out.println("Test Mono create");
        // Mono.delay() can create a Mono after duration with number 0
        // Mono.justOrEmpty() need a optional value
        // Mono.create() can create Mono too
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        return Mono.create(sink ->
                sink.success("Hello"));
    }

    @GetMapping("buffer")
    public Flux<List<Integer>> testBuffer() {
        System.out.println("Test Buffer");
        Flux<List<Integer>> buffer = Flux.range(1, 50).buffer(10);
        buffer.subscribe(System.out::println);
        //  System.out.println
        //  [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        //  [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
        //  [21, 22, 23, 24, 25, 26, 27, 28, 29, 30]
        //  [31, 32, 33, 34, 35, 36, 37, 38, 39, 40]
        //  [41, 42, 43, 44, 45, 46, 47, 48, 49, 50]
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        //  System.out.println
        //  [1, 2]
        //  [3, 4]
        //  [5, 6]
        //  [7, 8]
        //  [9, 10]
        System.out.println("-----------------------");
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
        //  System.out.println
        //  [2]
        //  [4]
        //  [6]
        //  [8]
        //  [10]
        return buffer;
    }

    @GetMapping("map")
    public Flux<Boolean> testMap() {
        System.out.println("Test Map");
        Flux<Boolean> map = Flux.range(1, 10).map(x -> x % 9 == 0);
        map.subscribe(System.out::println);
        //  System.out.println
        //  false
        //  false
        //  false
        //  false
        //  false
        //  false
        //  false
        //  false
        //  true
        //  false
        System.out.println("Test FlatMap");
        Flux<Integer> integerFlux = Flux.range(1, 5).flatMap(x -> Mono.just(x * x));
        integerFlux.subscribe(System.out::println);
        //  System.out.println
        //  1
        //  4
        //  9
        //  16
        //  25
        return map;
    }

    @GetMapping("window")
    public void testWindow() {
        System.out.println("Test Window");
        Flux.range(1, 5).window(2).toIterable().forEach(w -> {
            w.subscribe(System.out::println);
            System.out.println("-----------------------");
        });
        //  System.out.println
        //  1
        //  2
        //  -----------------------
        //  3
        //  4
        //  -----------------------
        //  5
        //  -----------------------
    }

    @GetMapping("filter")
    public void testFilter() {
        System.out.println("Test Filter");
        Flux.range(1, 10).filter(i -> i % 5 == 0).subscribe(System.out::println);
        // 5
        // 10
        System.out.println("-----------------------");
        Flux.range(1, 10).first().subscribe(System.out::println);
        // 1 ? I don't know why, but first() not worked
        System.out.println("-----------------------");
        Flux.range(1, 10).last().subscribe(System.out::println);
        // 10
        System.out.println("-----------------------");
        Flux.range(1, 10).skip(9).subscribe(System.out::println);
        // 10
        System.out.println("-----------------------");
        Flux.range(1, 10).skipLast(9).subscribe(System.out::println);
        // 1
        System.out.println("-----------------------");
        Flux.range(1, 10).take(2).subscribe(System.out::println);
        // 1
        // 2
        System.out.println("-----------------------");
        Flux.range(1, 10).takeLast(2).subscribe(System.out::println);
        // 9
        // 10
        System.out.println("-----------------------");
    }

    @GetMapping("operator")
    public void testOperator() {
        System.out.println("Test Then/When/Merge/StartWith/Zip");
        // .then()/.when() means do action after finished, .when() need all finished
        // .startWith() will insert into target seq with start poistion
        // .merge() will merge into target with actual create date sort
        // .mergeSequential will merge into target with actual subscription date sort
        // .zipWith() will merge element one to one

        System.out.println("Test DefaultIfEmpty/TakeUntil/TakeWhile/SkipUntil/SkipWhile");

        System.out.println("Test Concat/Count/Reduce");
    }

    @GetMapping("observable")
    public void testObservable() {
        System.out.println("Test Delay/Subscribe/Timeout/Block");
    }

    @GetMapping("log")
    public void testLog() {
        System.out.println("Test Log/Debug");
        Flux.range(1, 10).takeLast(2).log().subscribe(System.out::println);
        //  reactor.Flux.TakeLast.1 : onNext(9)
        //  9
        //  reactor.Flux.TakeLast.1 : onNext(10)
        //  10
        //  reactor.Flux.TakeLast.1 : onComplete()
        System.out.println("-----------------------");
        Hooks.onOperatorDebug();
        Flux.just(0).map(x -> 1 / x).checkpoint("zero").subscribe(System.out::println);
        //  Assembly trace from producer [reactor.core.publisher.FluxMapFuseable] :
        //    reactor.core.publisher.Flux.map(Flux.java:5730)
        //    cc.alpgo.srms.user.controller.TestController.testLog(TestController.java:184)
        //  Error has been observed by the following operator(s):
        //    Flux.map ⇢ cc.alpgo.srms.user.controller.TestController.testLog(TestController.java:184)
        //    Assembly site of producer [reactor.core.publisher.FluxOnAssembly] is identified by light checkpoint [zero].
        //
        //  Suppressed: reactor.core.publisher.FluxOnAssembly$SnapshotStackException: zero
        System.out.println("-----------------------");
    }
}
