<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Portfolio</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Portfolio</ion-title>
        </ion-toolbar>
      </ion-header>

exchangeprice
        <input type="text" v-model="newTransaction.exchangePrice">
        amountcoins
        <input type="text" v-model="newTransaction.amountCoins">
      <button @click="createNewTransaction">do transaction</button>
      <table>

        <tr>
          <th>Cash:</th>
          <td>{{ portfolio.cash }}</td>
        </tr>
      </table>
      transaktionen:
      {{portfolio.transactions}}
    </ion-content>
  </ion-page>
</template>

<script>
import { getPortfolio, postTransaction } from "@/api/portfolio";
import { Portfolio, Transaction } from "@/model/portfolio";
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  //IonCol,
  //IonRow,
  //IonGrid,
  //IonItem,
  //IonList,
  //IonButton,
  //IonInput,
} from "@ionic/vue";
import { onMounted, ref } from "vue";

export default {
  name: "Todo",
  components: {
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonPage,
    //IonCol,
    //IonRow,
    //IonGrid,
    //IonItem,
    //IonList,
    //IonButton,
    //IonInput,
  },
  setup() {
    const portfolio = ref<Portfolio>({});

    //const today = new Date();
    const newTransaction = ref<Transaction>({
        //date: `${today.getFullYear()}/${today.getMonth()}/${today.getDay()}`,
        exchangePrice: 1,
        amountCoins: 1
    });

    const loadPortfolio = async () => {
      try {
        portfolio.value = await getPortfolio();
      } catch (error) {
        console.log(error); // FIXME: Errorhandling
      }
    };

    const createNewTransaction = async () => {
      try {
        const res = await postTransaction(newTransaction.value);
        console.log({ res });
        loadPortfolio();
      } catch (error) {
        console.log(error); // FIXME: Errorhandling
      }
    };

    onMounted(loadPortfolio);

    return {
        newTransaction,
        createNewTransaction,
        portfolio,
    };
  },
};
</script>