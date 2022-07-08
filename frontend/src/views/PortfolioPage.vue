<template>
  <ion-page>
    <p></p>
    <ion-header>
      <ion-toolbar>
        <ion-title>
          <ion-col
            ><ion-text color="primary">
              <h2><strong>Portfolio</strong></h2></ion-text
            >
            <ion-text align="right">
              <h2><strong> Current Cash: {{ portfolio.cash }} USD</strong></h2></ion-text
            >
          
          </ion-col>
          <ion-col>
            
          </ion-col>
        </ion-title>
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
                <p>
                  <ion-col
                    >TRANSACTION:
                    <strong> {{ transaction.transactionId }}</strong>
                  </ion-col>
                  <ion-col>
                    <ion-text color="success"> <strong> BUY </strong></ion-text
                    ><strong>{{ transaction.symbol }}</strong></ion-col
                  >
                  <ion-col></ion-col>
                </p>

                <ion-col
                  >Amount:
                  <strong>{{ transaction.amountCoins }}</strong></ion-col
                >
                <ion-col
                  >Price:
                  <strong>{{ transaction.exchangePrice }} USD</strong></ion-col
                >
                <ion-col>
                  Date: <strong>{{ transaction.date }}</strong></ion-col
                >
              </div>
            </ion-row>
            <ion-row v-else>
              <div>
                <p>
                  <ion-col
                    >TRANSACTION:
                    <strong> {{ transaction.transactionId }}</strong>
                  </ion-col>
                  <ion-col>
                    <ion-text color="danger"> <strong> SELL </strong></ion-text
                    ><strong>{{ transaction.symbol }}</strong></ion-col
                  >
                  <ion-col></ion-col>
                </p>

                <ion-col
                  >Amount:
                  <strong>{{ transaction.amountCoins }}</strong></ion-col
                >
                <ion-col
                  >Price:
                  <strong>{{ transaction.exchangePrice }} USD</strong></ion-col
                >
                <ion-col>
                  Date: <strong>{{ transaction.date }}</strong></ion-col
                >
              </div>
            </ion-row>
          </ion-grid>
        </ion-item>
        <ion-item :key="portfolio">
          <ion-grid>
            <ion-row>
              <ion-col> </ion-col>
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
  },
};
</script>