package com.example.presta.publicidadexample.rest.post;

import android.os.AsyncTask;
import android.util.Log;

import com.example.presta.publicidadexample.common.CommonVariables;
import com.example.presta.publicidadexample.rest.ApiConstants;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Presta on 20/03/2016.
 */
// Abstrae una tarea de ejecutar un POST al servidor.
    // El contenido que viaja en el request es STRING y se pasa por parametro
    // La tarea es asincronica, y para conocer el resultado del post se implementa un callback.
    // El callback es el metodo onPostCompleted y lo debe implementar el invocador de esta clase.
public class PostRequestTask extends AsyncTask<String, Void, Integer> {

    private static final char PARAMETER_DELIMITER = '&';
    private static final char PARAMETER_EQUALS_CHAR = '=';
    private Integer CONNECTION_TIMEOUT = 60000;
    private Integer DATARETRIEVAL_TIMEOUT = 60000;
    private String LOGGER_TAG = "PostRequestTask";
    public static final int RETURN_MalformedURLException = 1;
    public static final int RETURN_SocketTimeoutException = 2;
    public static final int RETURN_IOException = 3;
    public static final int RETURN_OtherException = 4;


    private OnPostCompleted listener;
    private String metodoEjecutado;

    public PostRequestTask(OnPostCompleted listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... params) {

        // params[0] = metodo,  params[1] = parametros
        metodoEjecutado = params[0];
        try {
            return postData(params[0], params[1]);
        } catch (IOException e) {
            return RETURN_IOException;
        }
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(Integer result) {
        // Al finalizar ejecutamos el metodo onPostCompleted de la clase que invocó está clase.
        // Le pasamos el metodo ejecutado y el resultado.
        listener.onPostCompleted(metodoEjecutado, result);
    }

    private Integer postData(String metodo, String postParameters) throws IOException {

        HttpURLConnection urlConnection = null;
        Integer retorno = 0;

        try {

            // create connection
            URL urlToRequest = new URL(ApiConstants.URL_BASE + "/" + metodo);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            // handle POST parameters
            if (postParameters != null) {

                // Agregamos el uuid que tiene que estar en todos los request
                postParameters = "uuid=" + CommonVariables.GetUuid() + "&" + postParameters;

                Log.i(LOGGER_TAG, "POST parameters: " + postParameters);

                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setFixedLengthStreamingMode(postParameters.getBytes().length);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                //send the POST out
                PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
                out.print(postParameters);
                out.close();
            }

            // handle issues
            retorno = urlConnection.getResponseCode();
            if (retorno != HttpURLConnection.HTTP_OK) {

            }

        } catch (MalformedURLException e) {
            retorno = RETURN_MalformedURLException;
        } catch (SocketTimeoutException e) {
            retorno = RETURN_SocketTimeoutException;
        } catch (IOException e) {
            retorno = RETURN_IOException;
        } catch (Exception e) {
            retorno = RETURN_OtherException;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return retorno;
    }

    public static String createQueryStringForParameters(Map<String, String> parameters) {
        // Retorna un string del tipo “param1=value1&param2=value2”.

        StringBuilder parametersAsQueryString = new StringBuilder();
        if (parameters != null) {
            boolean firstParameter = true;

            for (String parameterName : parameters.keySet()) {
                if (!firstParameter) {
                    parametersAsQueryString.append(PARAMETER_DELIMITER);
                }

                parametersAsQueryString.append(parameterName)
                        .append(PARAMETER_EQUALS_CHAR)
                        .append(URLEncoder.encode(parameters.get(parameterName)));

                firstParameter = false;
            }
        }
        return parametersAsQueryString.toString();
    }
}
