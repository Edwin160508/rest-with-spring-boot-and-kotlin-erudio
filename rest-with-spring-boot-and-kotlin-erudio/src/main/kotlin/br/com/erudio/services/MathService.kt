package br.com.erudio.services

import br.com.erudio.exceptions.UnsuportedMathOperationException
import br.com.erudio.utils.MathUtils
import org.springframework.stereotype.Service

@Service
class MathService {

    fun sum( numberOne:String,
             numberTwo:String
    ):Double{
        if(!MathUtils.isNumeric(numberOne)||!MathUtils.isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return MathUtils.convertDouble(numberOne)+MathUtils.convertDouble(numberTwo);
    }

    fun subtract(numberOne:String,
                 numberTwo:String
    ):Double{
        if(!MathUtils.isNumeric(numberOne)||!MathUtils.isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return MathUtils.convertDouble(numberOne)-MathUtils.convertDouble(numberTwo);
    }

    fun multiply(numberOne:String,
                 numberTwo:String
    ):Double{
        if(!MathUtils.isNumeric(numberOne)||!MathUtils.isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return MathUtils.convertDouble(numberOne)*MathUtils.convertDouble(numberTwo);
    }

    fun divide(numberOne:String,
               numberTwo:String
    ):Double{
        if(!MathUtils.isNumeric(numberOne)||!MathUtils.isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return MathUtils.convertDouble(numberOne)/MathUtils.convertDouble(numberTwo);
    }

    fun mean(numberOne:String,
             numberTwo:String
    ):Double{
        if(!MathUtils.isNumeric(numberOne)||!MathUtils.isNumeric(numberTwo))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return (MathUtils.convertDouble(numberOne)+MathUtils.convertDouble(numberTwo)) / 2;
    }

    fun squareRoot(number:String
    ):Double{
        if(!MathUtils.isNumeric(number))
            throw UnsuportedMathOperationException("Please set a numeric value!");
        return Math.sqrt(MathUtils.convertDouble(number));
    }

}