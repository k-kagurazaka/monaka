package jp.keita.kagurazaka.monaka.demo

import rx.Observable
import rx.subjects.PublishSubject

interface Event

object EventEmitter {
    private val dispatcher = PublishSubject.create<Event>().toSerialized()

    fun dispatch(event: Event) {
        dispatcher.onNext(event)
    }

    fun observe(): Observable<Event> = dispatcher.asObservable()
}
