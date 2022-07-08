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
          <div>
            <p> <strong>TRANSACTION {{ transaction.transactionId }}: BUY {{ transaction.symbol }}</strong></p>
            <p> Amount: <strong>{{ transaction.amountCoins }}</strong>, Price: <strong>{{ transaction.exchangePrice }}</strong></p>
            <p>Date: {{ transaction.date }} </p>
          </div>
          </ion-row>
          <ion-row v-else>
            <div>
              <p> <strong>TRANSACTION {{ transaction.transactionId }}: SELL {{ transaction.symbol }}</strong></p>
            <p> Amount: <strong>{{ transaction.amountCoins }}</strong>, Price: <strong>{{ transaction.exchangePrice }}</strong></p>
            <p>Date: {{ transaction.date }} </p>
            </div>
          </ion-row>
        </ion-grid>
        </ion-item>
        <ion-item :key="portfolio">
        <ion-grid>
          <ion-row>
            <ion-col>
              <p>
                <strong>Current Cash: {{ portfolio.cash }}</strong>
              </p>
            </ion-col>
          </ion-row>
        </ion-grid>
        </ion-item>
      </ion-list>
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
} from "@ionic/vue";
import { useTransaction } from "@/composables/useTransaction";
import { usePortfolio } from "@/composables/usePortfolio";


export default {
  name: "PortfolioPage",
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
  },
  setup() {
    const { transactions } = useTransaction();
    const { portfolio } = usePortfolio();

    return { transactions, portfolio };
  }
}
</script>