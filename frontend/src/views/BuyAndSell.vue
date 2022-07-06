<template>
  <ion-page>
    <ion-card>
      <ion-card-header>
        <ion-card-title>Buy and sell coins</ion-card-title>
      </ion-card-header>
      <ion-card-content>
        <ion-list>
          <ion-item>
            <ion-select placeholder="Select currency" @ionChange="selectedCurrency = $event.detail.value; setCurrency()">
              <ion-select-option :value="symbol" :key="symbol" v-for="symbol in currencyNames">{{ symbol }}</ion-select-option>
            </ion-select>
            <p v-if="currency">{{ currency.historicalValues[currency.historicalValues.length - 1].value }}</p>
            
          </ion-item>
        </ion-list>
      </ion-card-content>
    </ion-card>
  </ion-page>
</template>

<script>
import {
  IonPage,
  IonCard,
  IonItem,
  IonList,
  IonSelect,
  IonSelectOption,
  IonCardTitle,
  IonCardHeader,
  IonCardContent
} from "@ionic/vue";
import { useCurrency } from "@/composables/useCurrency";

export default {
  name: "BuyAndSell",
  components: {
    IonPage,
    IonCard,
    IonItem,
    IonList,
    IonSelect,
    IonSelectOption,
    IonCardTitle,
    IonCardHeader,
    IonCardContent
  },
  setup() {
    const { currencyNames, currency, setCurrency, selectedCurrency } = useCurrency();

    return {currencyNames, currency, setCurrency, selectedCurrency};
  },
};
</script>

<style>
</style>