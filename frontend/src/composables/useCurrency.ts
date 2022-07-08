import { getAllCurrencyNames, getCurrency } from '@/api/currency';
import { Currency } from '@/model/currency';
import { onMounted, ref } from 'vue';

export function useCurrency() {

    const currencyNames = ref<string[]>([]);
    const currency = ref<Currency>();
    const selectedCurrency = ref<string>('Coins');
    const result = ref<number>();
    const amntCoins = ref<number>();

    //Get all currency names
    const getCurrencyNames = async () => {
        try {
            currencyNames.value = await getAllCurrencyNames();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    //Get historical values from the selected currency
    const getCurrencies = async () => {
        try {
            currency.value = await getCurrency(selectedCurrency.value);
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const setCurrency = async () => {
        getCurrencies();
    }

    //Calculate the amount currency bought/sold
    const calculateValue = async (value: number) => {
        if(amntCoins.value != null){
            result.value = Math.round(value * amntCoins.value * 100) / 100;
        }
        console.log(result.value);
    }

    onMounted(getCurrencyNames);
    return {currencyNames, currency, setCurrency, selectedCurrency, calculateValue, result, amntCoins};
}