import { Transaction } from "@/model/transaction";
import { onMounted, ref } from "vue";
import { addNewTransaction, getAllTransactions } from "@/api/transactions";
import { Currency } from "@/model/currency";
import { getCurrency } from "@/api/currency";


export function useTransaction() {

    const transactions = ref<Transaction[]>([]);

    const newTransaction = ref<Transaction>({});

    const currency = ref<Currency>();

    const amntCoinss = ref<number>();

    const selectedCurrencyyy = ref<string>('Test');

    const getTransactions = async () => {
        try {
            transactions.value = await getAllTransactions();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const addTransaction = async () => {
        try {

            currency.value = await getCurrency(selectedCurrencyyy.value);

            newTransaction.value.symbol = selectedCurrencyyy.value;

            newTransaction.value.amountCoins = amntCoinss.value;
            newTransaction.value.exchangePrice = currency.value.historicalValues[currency.value.historicalValues.length - 1].value;


            // add the new todo and update the list of all todos afterwards
            console.log(newTransaction.value);
            await addNewTransaction(newTransaction.value);
            getTransactions();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    onMounted(getTransactions);
    setInterval(getTransactions, 1000);

    return {
        transactions,
        newTransaction,
        addTransaction,
        amntCoinss,
        selectedCurrencyyy
    }
}