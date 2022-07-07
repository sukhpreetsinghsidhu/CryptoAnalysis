package com.example.cryptoanalysis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_coin_detail_page.*

class coin_detail_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail_page)

        var coin = "BTC"

        loadChart("1", coin)

        Btn1Min.setOnClickListener {
            loadChart("1", coin)
        }

        Btn15Min.setOnClickListener {
            loadChart("15", coin)
        }

        Btn1Hr.setOnClickListener {
            loadChart("1H", coin)
        }

        Btn1Day.setOnClickListener {
            loadChart("1D", coin)
        }

        Btn1Wk.setOnClickListener {
            loadChart("1W", coin)
        }

        Btn1Month.setOnClickListener {
            loadChart("1M", coin)
        }

        Btn1Year.setOnClickListener {
            loadChart("1Y", coin)
        }
    }

    private fun loadChart(s: String, coin: String){

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        webView.loadUrl(
            "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + coin +
                    "USD&interval=" + s + "&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=" +
                    "F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=" +
                    "[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaing=chart&utm_term=BTCUSDT"

        )
    }
}