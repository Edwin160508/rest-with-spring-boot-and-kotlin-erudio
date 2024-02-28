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
    fun greeting(@PathVariable(value = "numberOne") numberOne:String,
                 @PathVariable(value = "numberTwo") numberTwo:String
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return convertDouble(numberOne)+convertDouble(numberTwo);
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