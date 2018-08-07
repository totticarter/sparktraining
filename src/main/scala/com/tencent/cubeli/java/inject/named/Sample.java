package com.tencent.cubeli.java.inject.named;

/**
 * Created by liyong on 08/08/2018.
 */
import javax.inject.Named;
import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Names;

interface IHelloPrinter {
    void print();
}

@Singleton
class SimpleHelloPrinter implements IHelloPrinter {

    public void print() {
        System.out.println("Hello, Simple World");
    }

}

@Singleton
class ComplexHelloPrinter implements IHelloPrinter {

    public void print() {
        System.out.println("Hello, Complex World");
    }

}

class SampleModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IHelloPrinter.class).annotatedWith(Names.named("simplee")).to(SimpleHelloPrinter.class);
        bind(IHelloPrinter.class).annotatedWith(Names.named("complex")).to(ComplexHelloPrinter.class);
    }

}

@Singleton
public class Sample {

    @Inject
    @Named("simplee")
    private IHelloPrinter simplePrinter;

    @Inject
    @Named("complex")
    private IHelloPrinter complexPrinter;

    public void hello() {
        simplePrinter.print();
        complexPrinter.print();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SampleModule());
        Sample sample = injector.getInstance(Sample.class);
        sample.hello();
    }

}
