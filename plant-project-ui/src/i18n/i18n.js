import { createI18n } from "vue-i18n";
import enMessages from "./en";
import frMessages from "./fr";

const shortDatetimeFormat = {
  year: "numeric",
  month: "numeric",
  day: "2-digit",
};
const longDatetimeFormat = {
  year: "numeric",
  month: "long",
  day: "numeric",
};
const datetimeFormats = {
  en: {
    short: shortDatetimeFormat,
    long: shortDatetimeFormat,
  },
  fr: {
    short: shortDatetimeFormat,
    long: shortDatetimeFormat,
  },
};

const numberFormats = {
  en: {
    currency: {
      style: "currency",
      currency: "USD",
      notation: "standard",
    },
  },
  fr: {
    currency: {
      style: "currency",
      currency: "EUR",
      notation: "standard",
    },
  },
};

const i18n = createI18n({
  locale: "en",
  fallbackLocale: "en",
  messages: {
    en: enMessages,
    fr: frMessages,
  },
  datetimeFormats,
  numberFormats,
});

export default i18n;
