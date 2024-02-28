package br.com.erudio

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingsController {

    val counter  = AtomicLong();
    @RequestMapping("/greetings")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name:String):Greetings{
        return Greetings(counter.incrementAndGet(), "Hello $name")
    }
}