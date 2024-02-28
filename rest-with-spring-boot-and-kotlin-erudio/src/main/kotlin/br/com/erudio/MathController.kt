package br.com.erudio

import br.com.erudio.exceptions.UnsuportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController {

    val counter  = AtomicLong();
    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(@PathVariable(value = "numberOne") numberOne:String,
                 @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return convertDouble(numberOne)+convertDouble(numberTwo);
    }

    @RequestMapping(value = ["/subtract/{numberOne}/{numberTwo}"])
    fun subtract(@PathVariable(value = "numberOne") numberOne:String,
                 @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return convertDouble(numberOne)-convertDouble(numberTwo);
    }

    @RequestMapping(value = ["/multiply/{numberOne}/{numberTwo}"])
    fun multiply(@PathVariable(value = "numberOne") numberOne:String,
                 @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return convertDouble(numberOne)*convertDouble(numberTwo);
    }

    @RequestMapping(value = ["/divide/{numberOne}/{numberTwo}"])
    fun divide(@PathVariable(value = "numberOne") numberOne:String,
                 @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return convertDouble(numberOne)/convertDouble(numberTwo);
    }

    @RequestMapping(value = ["/mean/{numberOne}/{numberTwo}"])
    fun mean(@PathVariable(value = "numberOne") numberOne:String,
            @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return (convertDouble(numberOne)+convertDouble(numberTwo)) / 2;
    }

    @RequestMapping(value = ["/squareRoot/{number}"])
    fun squareRoot(@PathVariable(value = "number") number:String
    ):Double{
        if(!isNumeric(number))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return Math.sqrt(convertDouble(number));
    }
    private fun isNumeric(strNumber: String):Boolean{
         if(strNumber.isNullOrBlank()) return false
         val number = strNumber.replace(",".toRegex(), ".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }

    private fun convertDouble(strNumber: String):Double{
        if(strNumber.isNullOrBlank()) return 0.0
        val number = strNumber.replace(",".toRegex(), ".")
        return if(isNumeric(number)) number.toDouble() else 0.0
    }
}