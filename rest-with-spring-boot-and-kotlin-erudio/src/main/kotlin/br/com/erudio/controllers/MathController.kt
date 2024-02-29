package br.com.erudio.controllers

import br.com.erudio.services.MathService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MathController {

    @Autowired
    private val service: MathService = MathService();
    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(@PathVariable(value = "numberOne") numberOne:String,
                 @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{

        return service.sum(numberOne, numberTwo);
    }

    @RequestMapping(value = ["/subtract/{numberOne}/{numberTwo}"])
    fun subtract(@PathVariable(value = "numberOne") numberOne:String,
                 @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{

        return service.subtract(numberOne, numberTwo)
    }

    @RequestMapping(value = ["/multiply/{numberOne}/{numberTwo}"])
    fun multiply(@PathVariable(value = "numberOne") numberOne:String,
                 @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{

        return service.multiply(numberOne,numberTwo)
    }

    @RequestMapping(value = ["/divide/{numberOne}/{numberTwo}"])
    fun divide(@PathVariable(value = "numberOne") numberOne:String,
                 @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{

        return service.divide(numberOne,numberTwo)
    }

    @RequestMapping(value = ["/mean/{numberOne}/{numberTwo}"])
    fun mean(@PathVariable(value = "numberOne") numberOne:String,
            @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{
        return service.mean(numberOne, numberTwo)
    }

    @RequestMapping(value = ["/squareRoot/{number}"])
    fun squareRoot(@PathVariable(value = "number") number:String
    ):Double{
        return service.squareRoot(number);
    }
}