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
    const selectedCurrencyyy = ref<string>('Currency');

    const getTransactions = async () => {
        try {
            transactions.value = await getAllTransactions();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const addTransaction = async (buy: boolean) => {
        try {
            //Check if amount is not empty and not 0
            if(amntCoinss.value != null && amntCoinss.value != 0){

                currency.value = await getCurrency(selectedCurrencyyy.value);
                newTransaction.value.symbol = selectedCurrencyyy.value;
                newTransaction.value.buy = buy;
                newTransaction.value.amountCoins = amntCoinss.value;
                //If user buys coins
                if(buy){
                    newTransaction.value.exchangePrice = currency.value.historicalValues[currency.value.historicalValues.length - 1].value;
                }else{
                    //If user sells coins (second last value of the currency is taken)
                    newTransaction.value.exchangePrice = currency.value.historicalValues[currency.value.historicalValues.length - 2].value;
                }
                await addNewTransaction(newTransaction.value);
                getTransactions();
            }
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