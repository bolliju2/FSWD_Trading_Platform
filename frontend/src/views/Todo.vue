<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Transactions</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Transactions</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-list>
        <ion-item :key="transaction" v-for="transaction in transactions">
        <ion-grid>
          <ion-row v-if="transaction.buy">
            <p>Transaction {{ transaction.transactionId }}: Buy {{ transaction.symbol }}, Amount: {{ transaction.amountCoins }}, Price: {{ transaction.exchangePrice }}, Date: {{ transaction.date }} </p>
          </ion-row>
          <ion-row v-else>
            <p>Transaction {{ transaction.transactionId }}: Sell {{ transaction.symbol }}, Amount: {{ transaction.amountCoins }}, Price: {{ transaction.exchangePrice }}, Date: {{ transaction.date }} </p>
          </ion-row>
        </ion-grid>
        </ion-item>
        <ion-item :key="portfolio">
        <ion-grid>
          <ion-row>
            <ion-col>
              <p>
                Current Cash: <strong>{{ portfolio.cash }}</strong>
              </p>
            </ion-col>
          </ion-row>
        </ion-grid>
        </ion-item>
        <!-- <ion-item :key="todo" v-for="todo in todos">
          <ion-grid>
            <ion-row>
              <ion-col>
                {{ todo.title }}
              </ion-col>
              <ion-col>
                <ion-button
                  color="danger"
                  v-if="!todo.done && !todo.archived"
                  @click="finishTodo(todo)"
                  >Finish</ion-button
                >
                <ion-button
                  color="success"
                  v-if="todo.done && !todo.archived"
                  @click="archiveTodo(todo)"
                  >Archive</ion-button
                >
              </ion-col>
            </ion-row>
          </ion-grid>
        </ion-item> -->
      </ion-list>
      <!-- <ion-item>
        <ion-input
          type="text"
          placeholder="New Todo Title"
          v-model="newTodo.title"
        ></ion-input>
      </ion-item> -->
      <!-- <div padding>
        <ion-button @click="addTodo()">Add New ToDo</ion-button>
      </div> -->
    </ion-content>
  </ion-page>
</template>

<script>
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonCol,
  IonRow,
  IonGrid,
  IonItem,
  IonList,
  //IonButton,
  //IonInput,
} from "@ionic/vue";
//import { useTodos } from "@/composables/useTodos";
import { useTransaction } from "@/composables/useTransaction";
import { usePortfolio } from "@/composables/usePortfolio";

// export default {
//   name: "Todo",
//   components: {
//     IonHeader,
//     IonToolbar,
//     IonTitle,
//     IonContent,
//     IonPage,
//     IonCol,
//     IonRow,
//     IonGrid,
//     IonItem,
//     IonList,
//     IonButton,
//     IonInput,
//   },
//   setup() {
//     const { newTodo, todos, getTodos, addTodo, finishTodo, archiveTodo } = useTodos();

//     return { newTodo, todos, getTodos, addTodo, finishTodo, archiveTodo };
//   },
// };

export default {
  name: "Transaction",
  components: {
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonPage,
    IonCol,
    IonRow,
    IonGrid,
    IonItem,
    IonList,
    //IonButton,
    //IonInput,
  },
  setup() {
    const { transactions } = useTransaction();
    const { portfolio } = usePortfolio();

    return { transactions, portfolio };
  }
}
</script>