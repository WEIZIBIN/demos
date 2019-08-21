package github.weizibin.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weizibin
 * @since 2019-08-21 14:09
 */
public class ObservePattern  {

    private interface Subject {
        void addObserver(Observer observer);
        void event(Event e);
    }

    private interface Observer {
        void subscribe(Event e);
    }

    private static class SubjectImpl implements Subject {
        private List<Observer> observerList = new ArrayList<>();

        @Override
        public void addObserver(Observer observer) {
            observerList.add(observer);
        }

        @Override
        public void event(Event e) {
            for (Observer observer : observerList) {
                observer.subscribe(e);
            }
        }
    }



    private static class PrintObserverImpl implements Observer {
        @Override
        public void subscribe(Event e) {
            System.out.println(e);
        }
    }

    private interface Event {

    }

    private static class HelloEvent implements Event {

    }

    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
        Observer observer = new PrintObserverImpl();
        Event e = new HelloEvent();
        subject.addObserver(observer);
        subject.event(e);
    }

}
