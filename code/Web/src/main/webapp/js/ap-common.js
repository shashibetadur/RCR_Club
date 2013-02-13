function canParseNumber(value){
    var numberRegex =/^\s*\d+\s*$/;
    if(numberRegex.test(value)){
        return true;
    }
    return false;
}

function canParseOnlyAlphabets(value){
    var numberRegex =/^[a-zA-Z]+$/;
    if(numberRegex.test(value)){
        return true;
    }
    return false;
}
function canParseOnlyAlphabetsWithSpace(value){
    var numberRegex =/^[a-zA-Z ]+$/;
    if(numberRegex.test(value)){
        return true;
    }
    return false;
}
function canParseOnlyAlphaNumericWithSpace(value){
    var numberRegex =/^[a-zA-Z0-9 ]+$/;
    if(numberRegex.test(value)){
        return true;
    }
    return false;
}

function canParseOnlyAlphaNumeric(value){
    var numberRegex =/^[a-zA-Z0-9]+$/;
    if(numberRegex.test(value)){
        return true;
    }
    return false;
}

function canParseDecimal(value){
    var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
    if(floatRegex.test(value)){
        return true;
    }
    return false;
}

function specialTrim(value){
    if(!value) return value;
    return value.trim().replace( /\s\s+/g, ' ' );
}

function isEmpty(value){
    if(value && value.trim()){
        return false;
    }
    return true;
}

function canParseDate(dateString){
    try{
        $.datepicker.parseDate('dd-mm-yyyy', dateString);
        return true;
    }catch(err){
        return false;
    }
}

function jqVal(selector){
    return $(selector).val();
}