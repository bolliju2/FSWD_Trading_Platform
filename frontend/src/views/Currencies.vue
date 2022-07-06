<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Currencies</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Currencies</ion-title>
        </ion-toolbar>
      </ion-header>

      <div :key="currency" v-for="currency in currencies">
        <h3>{{ currency.name }}</h3>
        <line-chart :chart-data="toChartData(currency)"></line-chart>
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { getCurrency } from "@/api/currency";
import { getPortfolio, postTransaction } from "@/api/portfolio";
import { Currency } from "@/model/currency";
import { Portfolio, Transaction } from "@/model/portfolio";
import { Line } from "vue-chartjs";
import moment from "moment";
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

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

export default {
  name: "Todo",
  components: {
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonPage,
    "line-chart": Line,
    //IonCol,
    //IonRow,
    //IonGrid,
    //IonItem,
    //IonList,
    //IonButton,
    //IonInput,
  },
  setup() {
    const currencies = ref<Currency[]>([]);

    const toChartData = (currency: Currency) => {
        const labels = []
        const data = []
        for( const currencyValue of currency.historicalValues) {
            labels.push(moment(currencyValue.timestamp).format('DD.MM.YYYY'))
            data.push(currencyValue.value)
        }
        return {
            labels,
            datasets: [
                {
                    data, 
                    label: 'DOGE',
                    fill: false,
          borderColor: "#3e95cd",
                }
            ]

        }
    };
    const loadCurrencies = async () => {
      const currs = ["DOGE", "OG", "BTC", "ETH"];

      for (const c of currs) {
        const currency = await getCurrency(c);
        currency.name = c;
        currencies.value.push(currency);
      }
    };

    onMounted(loadCurrencies);

    return {
      currencies,
      toChartData
    };
  },
};
</script>