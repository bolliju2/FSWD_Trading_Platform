<template>
  <ion-page>
    <ion-content fullscreen>
      <div class="card">
        <ion-card>
        <ion-card-header>
          <ion-card-title>
            <h1>
              Buy and sell coins
            </h1>
          </ion-card-title>
          </ion-card-header>
          <ion-card-content>
            <ion-list>
              <ion-item>
                <ion-select placeholder="Choose a currency" @ionChange="selectedCurrency = $event.detail.value; setCurrency(); selectedCurrencyyy = $event.detail.value">
                  <ion-select-option :value="symbol" :key="symbol" v-for="symbol in currencyNames">
                    {{ symbol }}
                  </ion-select-option>
                </ion-select>
              </ion-item>
              <ion-item>
                <h2>
                  Current value:
                </h2>
              </ion-item>
              <ion-item v-if="currency">
                <h2 class="value">
                  {{ currency.historicalValues[currency.historicalValues.length - 1].value }}
                </h2>
              </ion-item>
              <ion-item>
                <ion-input type="number" placeholder="Enter the amount of coins" @ionChange="amntCoinss = $event.detail.value" v-model="amntCoins">
                </ion-input>
              </ion-item>
              <ion-grid fixed>
                <ion-row>
                  <ion-col>
                    <ion-button color="success" class="btn" @click="calculateValue(currency.historicalValues[currency.historicalValues.length - 1].value); addTransaction(true); presentAlert()">
                      Buy {{ selectedCurrency }}
                    </ion-button>
                  </ion-col>
                  <ion-col>
                    <ion-button color="danger" class="btn" @click="calculateValue(currency.historicalValues[currency.historicalValues.length - 2].value); addTransaction(false); presentAlert()">
                      Sell {{ selectedCurrency }}
                    </ion-button>
                  </ion-col>
                </ion-row>
              </ion-grid>
              <ion-item>
                <h2>
                  Total value:
                </h2>
              </ion-item>
              <ion-item>
                <h2 class="value">
                   {{ result }} 
                </h2>
              </ion-item>
            </ion-list>
          </ion-card-content>
        </ion-card>
      </div>
    </ion-content>
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
  IonContent,
  IonGrid,
  IonRow,
  IonCol,
  alertController 
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
    IonContent,
    IonGrid,
    IonRow,
    IonCol
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

    const presentAlert = async () => {
      const alert = await alertController.create({
        header: 'Success',
        subHeader: 'Transaction was successful',
        message: 'Your Transactions has been added to your portfolio',
        buttons: ['OK']
      });
      await alert.present();

    }
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
      amntCoinss,
      presentAlert
    };
  },
};
</script>

<style>
.card {
  width: 50%;
 margin: auto;
 margin-top: 10%;
}

.value {
  font-weight: 600;
}

.btn {
  width: 100%;
 margin: auto;
}
</style>