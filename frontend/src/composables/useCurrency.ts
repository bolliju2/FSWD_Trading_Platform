import { getAllCurrencyNames, getCurrency } from '@/api/currencyTest';
import { Currency } from '@/model/currency';
import { onMounted, ref } from 'vue';

export function useCurrency() {

    const currencyNames = ref<string[]>([]);
    const currency = ref<Currency>();
    const selectedCurrency = ref<string>('Test');

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
            console.log(currency.value);
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const setCurrency = async () => {
        getCurrencies();
    }

    onMounted(getCurrencyNames);
    onMounted(getCurrencies);
    return {currencyNames, currency, setCurrency, selectedCurrency};
}