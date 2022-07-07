import { getAllCurrencyNames, getCurrency } from '@/api/currencyTest';
import { Currency } from '@/model/currency';
import { onMounted, ref } from 'vue';

export function useCurrency() {

    const currencyNames = ref<string[]>([]);
    const currency = ref<Currency>();
    const selectedCurrency = ref<string>('Test');
    const result = ref<number>();
    const amntCoins = ref<number>();

    const getCurrencyNames = async () => {
        try {
            currencyNames.value = await getAllCurrencyNames();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

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

    const calculateValue = async (wechselK: number) => {
        if(amntCoins.value != null){
            result.value = Math.round(wechselK * amntCoins.value); //HOW TO ROUND
        }
        console.log(result.value);
    }

    onMounted(getCurrencyNames);
    return {currencyNames, currency, setCurrency, selectedCurrency, calculateValue, result, amntCoins};
}