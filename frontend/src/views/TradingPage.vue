<template>
  <ion-page>
    <ion-card>
      <ion-card-header>
        <ion-card-title>Buy and sell coins</ion-card-title>
      </ion-card-header>      
      <ion-card-content>
        <ion-list>
          <ion-item>
            <ion-select
              placeholder="Select currency" @ionChange="selectedCurrency = $event.detail.value; setCurrency(); selectedCurrencyyy = $event.detail.value">
              <ion-select-option
                :value="symbol"
                :key="symbol"
                v-for="symbol in currencyNames"
                >{{ symbol }}</ion-select-option
              >
            </ion-select>
          </ion-item>
          <ion-item>
            <p v-if="currency">
              Current Value: <strong> {{ currency.historicalValues[currency.historicalValues.length - 1].value }} </strong>
            </p>
          </ion-item>
          <ion-item>
            <ion-input
              type="number"
              placeholder="Enter the amount of coins" 
              @ionChange="amntCoinss = $event.detail.value" v-model="amntCoins"
              >
            </ion-input>
          </ion-item>
          <ion-item>
            <ion-button
              @click="calculateValue(currency.historicalValues[currency.historicalValues.length - 1].value); addTransaction(true)">Buy {{ selectedCurrency }}
            </ion-button>
            <ion-button
              @click="calculateValue(currency.historicalValues[currency.historicalValues.length - 2].value); addTransaction(false)">Sell {{ selectedCurrency }}
            </ion-button>
          </ion-item>
          <ion-item>
            <h3>Total: <strong> {{ result }} </strong> </h3>
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
  IonCardContent,
  IonInput,
  IonButton,
} from "@ionic/vue";
import { useCurrency } from "@/composables/useCurrency";
import { useTransaction } from "@/composables/useTransaction";

export default {
  name: "TradingPage",
  components: {
    IonPage,
    IonCard,
    IonItem,
    IonList,
    IonSelect,
    IonSelectOption,
    IonCardTitle,
    IonCardHeader,
    IonCardContent,
    IonInput,
    IonButton,
  },
  setup() {
    const {
      currencyNames,
      currency,
      setCurrency,
      selectedCurrency,
      calculateValue,
      result,      
      amntCoins,
    } = useCurrency();
    const {  
        addTransaction,
        selectedCurrencyyy,
        amntCoinss
      } = useTransaction();

    return {
      currencyNames,
      currency,
      setCurrency,
      selectedCurrency,
      calculateValue,
      result,
      amntCoins,
      addTransaction,
      selectedCurrencyyy,
      amntCoinss
    };
  },
};
</script>

<style>

</style>