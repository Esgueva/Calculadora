package com.esgueva.ejercicio2_calculadora;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity {

    private TextView txtResultado, txtHistorico;
    private String opAnterior = "+";
    private boolean coma = false, operacion = false, primerUso = true, error = false;
    private double nNuevo, nAnterior = 0;
    private Integer antiloop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        Button btnCero = (Button) findViewById(R.id.btnCero);
        Button btnUno = (Button) findViewById(R.id.btnUno);
        Button btnDos = (Button) findViewById(R.id.btnDos);
        Button btnTres = (Button) findViewById(R.id.btnTres);
        Button btnCuatro = (Button) findViewById(R.id.btnCuatro);
        Button btnCinco = (Button) findViewById(R.id.btnCinco);
        Button btnSeis = (Button) findViewById(R.id.btnSeis);
        Button btnSiete = (Button) findViewById(R.id.btnSiete);
        Button btnOcho = (Button) findViewById(R.id.btnOcho);
        Button btnNueve = (Button) findViewById(R.id.btnNueve);
        Button btnSumaResta = (Button) findViewById(R.id.btnSumaResta);
        Button btnComa = (Button) findViewById(R.id.btnComa);
        Button btnSuma = (Button) findViewById(R.id.btnSuma);
        Button btnResta = (Button) findViewById(R.id.btnResta);
        Button btnDivi = (Button) findViewById(R.id.btnDivi);
        Button btnMulti = (Button) findViewById(R.id.btnMulti);
        Button btnIgual = (Button) findViewById(R.id.btnIgual);
        Button btnBorrarCE = (Button) findViewById(R.id.btnBorradoCE);
        Button btnBorrarC = (Button) findViewById(R.id.btnBorradoC);
        Button btnBorrado = (Button) findViewById(R.id.btnBorrado);
        Button btnPorciento = (Button) findViewById(R.id.btnPorciento);
        Button btnRaiz = (Button) findViewById(R.id.btnRaiz);
        Button btnPotencia = (Button) findViewById(R.id.btnPotencia);
        Button btnFraccion = (Button) findViewById(R.id.btnFraccion);

        btnCero.setOnClickListener(escuchaNumero);
        btnUno.setOnClickListener(escuchaNumero);
        btnDos.setOnClickListener(escuchaNumero);
        btnTres.setOnClickListener(escuchaNumero);
        btnCuatro.setOnClickListener(escuchaNumero);
        btnCinco.setOnClickListener(escuchaNumero);
        btnSeis.setOnClickListener(escuchaNumero);
        btnSiete.setOnClickListener(escuchaNumero);
        btnOcho.setOnClickListener(escuchaNumero);
        btnNueve.setOnClickListener(escuchaNumero);
        btnSumaResta.setOnClickListener(escuchaSimbolo);
        btnComa.setOnClickListener(escuchaSimbolo);
        btnSuma.setOnClickListener(escuchaSimbolo);
        btnResta.setOnClickListener(escuchaSimbolo);
        btnMulti.setOnClickListener(escuchaSimbolo);
        btnDivi.setOnClickListener(escuchaSimbolo);
        btnIgual.setOnClickListener(escuchaSimbolo);
        btnBorrarCE.setOnClickListener(escuchaSimbolo);
        btnBorrarC.setOnClickListener(escuchaSimbolo);
        btnBorrado.setOnClickListener(escuchaSimbolo);
        btnPorciento.setOnClickListener(escuchaSimbolo);
        btnRaiz.setOnClickListener(escuchaSimbolo);
        btnPotencia.setOnClickListener(escuchaSimbolo);
        btnFraccion.setOnClickListener(escuchaSimbolo);

        txtResultado = (TextView) findViewById(R.id.txtResultado);
        txtHistorico = (TextView) findViewById(R.id.txtHistorico);
    }

    private void setup(String opNuevo, View view) {
        antiloop();
        if (!primerUso) {
            if (!operacion) {
                calculoMatematico(opNuevo, view);
            } else if (opAnterior.compareTo("=") != 0) {
                String text = txtHistorico.getText().toString();
                text = text.substring(0, text.length() - 1) + opNuevo;

                txtHistorico.setText(text);
            }
            opAnterior = opNuevo;
        } else {
            warningSinNumero(view);
        }
        antiloop = 0;
    }

    private void antiloop() {
        if (antiloop == 1) {
            nNuevo = 0;
            if (opAnterior.compareTo("*") == 0 || opAnterior.compareTo("/") == 0) {
                nNuevo = 1;
            }
        } else {
            nNuevo = Double.parseDouble(txtResultado.getText().toString());
        }
    }

    //--EVENTOS ---------------------------------------------------------------------------//

    private void calculoMatematico(String opNuevo, View view) {
        if (opAnterior.equalsIgnoreCase("+")) {
            nAnterior += nNuevo;
        } else if (opAnterior.equalsIgnoreCase("-")) {
            nAnterior -= nNuevo;
        } else if (opAnterior.equalsIgnoreCase("*")) {
            nAnterior *= nNuevo;
        } else if (opAnterior.equalsIgnoreCase("/")) {
            nAnterior /= nNuevo;
        } else if (opAnterior.equalsIgnoreCase("=")) {
            if (antiloop == 0) {
                antiloop++;
                opAnterior = opNuevo;
                setup(opNuevo, view);
            }
        }
        coma = false;
        operacion = true;
        txtResultado.setText(String.valueOf(nAnterior));
        if (!opNuevo.equalsIgnoreCase("=") && antiloop == 0) {
            txtHistorico.setText(txtHistorico.getText().toString() + opNuevo);
        }
        if (opNuevo.equalsIgnoreCase("=")) {
            txtHistorico.setText(txtResultado.getText().toString());
        }
    }

    private View.OnClickListener escuchaSimbolo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pulsacionSimbolo(view);
        }
    };

    private View.OnClickListener escuchaNumero = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pulsacionNumero(view);
        }
    };

    private void pulsacionSimbolo(View view) {
        if (!txtResultado.getText().toString().equalsIgnoreCase("")) {
            switch (view.getId()) {
                case R.id.btnSuma:
                    setup("+", view);
                    break;

                case R.id.btnResta:
                    setup("-", view);
                    break;

                case R.id.btnMulti:
                    setup("*", view);
                    break;

                case R.id.btnDivi:
                    setup("/", view);
                    break;

                case R.id.btnIgual:
                    setup("=", view);
                    operacion = false;
                    break;

                case R.id.btnSumaResta:
                    masMenos(view);
                    break;

                case R.id.btnComa:
                    coma();
                    break;

                case R.id.btnBorradoCE:
                    borrarCE(view);
                    break;

                case R.id.btnBorradoC:
                    borrarC(view);
                    break;

                case R.id.btnBorrado:
                    suprimirUltimo(view);
                    break;

                case R.id.btnPorciento:
                    porciento(view);
                    break;

                case R.id.btnRaiz:
                    raiz(view);
                    break;

                case R.id.btnPotencia:
                    potencia(view);
                    break;

                case R.id.btnFraccion:
                    fraccion(view);
                    break;
            }
        } else {
            warningSinNumero(view);
        }
    }

    private void pulsacionNumero(View view) {
        if (operacion) {
            txtResultado.setText("");
            if (opAnterior.compareTo("=") == 0) {
                opAnterior = "";
            }
        }
        if (error) {
            txtHistorico.setText("");
            txtResultado.setText("");
            error = false;
            nAnterior = 0;
        }
        if (opAnterior.equalsIgnoreCase("=")) {
            txtResultado.setText("");
            txtHistorico.setText("");
        }
        txtResultado.setText(txtResultado.getText().toString() + view.getTag().toString());
        txtHistorico.setText(txtHistorico.getText().toString() + view.getTag().toString());
        primerUso = false;
        operacion = false;
    }

    //--SECUNDARIOS------------------------------------------------------------------------//

    private void coma() {
        if (!coma) {// SI ESTA VACIO INCORPORAR UN CERO ANTES DE LA COMA Y EVITA METER MAS DE UNA COMA
            if (opAnterior.equalsIgnoreCase("=")) {
                txtResultado.setText("");
                txtHistorico.setText("");
                nAnterior = 0;
                opAnterior = "";
            }
            if ((txtResultado.getText().toString()).equalsIgnoreCase("")) {
                txtResultado.setText("0.");
                txtHistorico.setText(txtHistorico.getText().toString() + "0.");
            } else {
                txtResultado.setText(txtResultado.getText().toString() + ".");
                txtHistorico.setText(txtHistorico.getText().toString() + ".");
            }
            coma = true;
            operacion = false;
        }
    }

    private void fraccion(View view) {
        String frac = (String) txtResultado.getText();
        if (!frac.equalsIgnoreCase("")) {
            double fraccion = 1 / Double.valueOf(frac);
            txtResultado.setText(String.valueOf(fraccion));
            txtHistorico.setText("1/" + frac + "=" + txtResultado.getText());
            opAnterior = "";
            nAnterior = Double.parseDouble((String) txtResultado.getText());
        } else {
            warningSinNumero(view);
        }
    }

    private void masMenos(View view) {
        String resultado = txtResultado.getText().toString();
        if (!resultado.equalsIgnoreCase("")) {
            nAnterior = Double.parseDouble(resultado) * (-1);
            txtResultado.setText(String.valueOf(nAnterior));
            txtHistorico.setText(String.valueOf(nAnterior));
        } else {
            warningSinNumero(view);
        }
    }

    private void porciento(View view) {
        String resultado = txtResultado.getText().toString();
        if (!resultado.equalsIgnoreCase("")) {
            double dato = Double.parseDouble(txtResultado.getText().toString()) / 100;
            nAnterior = dato;
            txtHistorico.setText(txtResultado.getText().toString() + "/100=" + dato);
            txtResultado.setText(String.valueOf(nAnterior));
            opAnterior = "";
            operacion = true;
        } else {
            warningSinNumero(view);
        }
    }

    private void potencia(View view) {
        String pot = txtResultado.getText().toString();
        if (!pot.equalsIgnoreCase("")) {
            double potencia = Double.valueOf(pot);
            txtResultado.setText(String.valueOf(potencia * potencia));
            txtHistorico.setText(pot + "²=" + txtResultado.getText());
            opAnterior = "";
            nAnterior = Double.parseDouble((String) txtResultado.getText());
            operacion = true;
        } else {
            warningSinNumero(view);
        }
    }

    private void raiz(View v) {
        String raiz = (String) txtResultado.getText();
        double datoRaiz = Double.parseDouble(raiz);
        if (datoRaiz > 0 && !raiz.equalsIgnoreCase("")) {
            double solucion = Math.sqrt(datoRaiz);
            String resultado = String.valueOf(solucion);
            txtResultado.setText(resultado);
            txtHistorico.setText("√" + raiz + "=" + txtResultado.getText());
            opAnterior = "";
            nAnterior = Double.parseDouble((String) txtResultado.getText());
            operacion = true;
        } else {
            warningNaN(v);
            error = true;
        }
    }

    //--DELETES----------------------------------------------------------------------------//

    private void borrarCE(View v) {
        String txtSuprimir = txtResultado.getText().toString();
        if (txtSuprimir.length() == 0) {
            warningSinNumero(v);
        } else {
            txtResultado.setText("");
            txtHistorico.setText("");
            primerUso = true;
            error = false;
            coma = false;
            operacion = false;
            nAnterior = 0;
            nNuevo = 0;
            opAnterior = "+";
        }
    }

    private void borrarC(View v) {
        if(opAnterior.equalsIgnoreCase("=")){
            borrarCE(v);
        }else {
            String txtSuprimir = txtResultado.getText().toString();
            String historico = txtHistorico.getText().toString().substring(0, txtHistorico.getText().length() - txtSuprimir.length());
            txtHistorico.setText(historico);
            txtResultado.setText("");
            coma = false;
            operacion = false;
            primerUso = false;
            nNuevo = 0;
        }
    }

    private void suprimirUltimo(View v) {
        if (opAnterior.equalsIgnoreCase("=")) {
            borrarCE(v);
        }
        String txtSuprimir = txtResultado.getText().toString();
        String txtSupHistorico = txtHistorico.getText().toString();
        if (txtSuprimir.length() == 0) {
            borrarCE(v);
        } else {
            txtSuprimir = txtSuprimir.substring(0, txtSuprimir.length() - 1);
            txtSupHistorico = txtSupHistorico.substring(0, txtSupHistorico.length() - 1);
            txtResultado.setText(txtSuprimir);
            txtHistorico.setText(txtSupHistorico);
        }
    }

    //--EXCEPCIONES-----------------------------------------------------------------------//

    private void warningSinNumero(View v) {
        Snackbar.make(v, getResources().getString(R.string.error), Snackbar.LENGTH_LONG).show();
    }

    private void warningNaN(View v) {
        Snackbar.make(v, getResources().getString(R.string.errorRaiz), Snackbar.LENGTH_LONG).show();
    }
}