import { Transaction } from "@/model/transaction";
import { onMounted, ref } from "vue";
import { getAllTransactions } from "@/api/transactions";

export function useTransaction() {

    const transactions = ref<Transaction[]>([]);


    const getTransactions = async () => {
        try {
            transactions.value = await getAllTransactions();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    // const finishTodo = async (todo: ToDo) => {
    //     try {
    //         todo.done = true;
    //         updateToDo(todo);
    //     } catch (error) {
    //         console.log(error); // FIXME: Errorhandling
    //     }
    // }

    // const archiveTodo = async (todo: ToDo) => {
    //     try {
    //         todo.archived = true;
    //         await updateToDo(todo);
    //         getTodos();
    //     } catch (error) {
    //         console.log(error); // FIXME: Errorhandling
    //     }
    // }

    // const addTodo = async () => {
    //     try {
    //         // add the new todo and update the list of all todos afterwards
    //         await addNewToDo(newTodo.value);
    //         getTodos();
    //     } catch (error) {
    //         console.log(error); // FIXME: Errorhandling
    //     }
    // }

    onMounted(getTransactions);

    return {
        transactions,
        // todos,
        // getTodos,
        // addTodo,
        // finishTodo,
        // archiveTodo
    }
}