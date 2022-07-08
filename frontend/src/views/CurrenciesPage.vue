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
      <div class="chart" :key="currency" v-for="currency in currencies">
        <ion-card>
          <ion-card-header>
            <ion-card-title>
              <h1>
                {{ currency.symbol }}
              </h1>
            </ion-card-title>
          </ion-card-header>
          <ion-card-content>
            <line-chart :chart-data="toChartData(currency)"></line-chart>
          </ion-card-content>
        </ion-card>
        <!-- <h3>{{ currency.symbol }}</h3>
        <line-chart :chart-data="toChartData(currency)"></line-chart> -->
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { getCurrency } from "@/api/currency";
import { Currency } from "@/model/currency";
import { Line } from "vue-chartjs";
import moment from "moment";
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle
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
  Legend,
);

export default {
  name: "CurrenciesPage",
  components: {
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonPage,
    "line-chart": Line,
    IonCard,
    IonCardContent,
    IonCardHeader,
    IonCardTitle
  },
  setup() {
    const currencies = ref<Currency[]>([]);

    //Function to create a chart
    const toChartData = (currency: Currency) => {
        const labels = []
        const data = []
        for( const currencyValue of currency.historicalValues) {
            labels.push(moment(currencyValue.timestamp).format('DD.MM.YY'))
            data.push(currencyValue.value)
        }
        return {
            labels,
            datasets: [
                {
                    data, 
                    label: currency.symbol,
                    fill: false,
          borderColor: "#3e95cd",
                }
            ]

        }
    };

    //Function to load all currencies
    const loadCurrencies = async () => {
      const currs = ["DOGE", "OG", "BTC", "ETH"];

      for (const c of currs) {
        const currency = await getCurrency(c);
        currency.symbol = c;
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

<style>
.chart {
  margin: auto;
  width: 80%;
}
</style>