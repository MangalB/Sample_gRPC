package ai.man.al.samplegrpc;

import android.content.Context;
import android.os.AsyncTask;

import customers.isregistered.Isregistered;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import services.customersGrpc;

public class IsRegisteredATask extends AsyncTask<Void, Void, Boolean> {

    private final String mobile;
    private TaskListener taskListener;

    public IsRegisteredATask(String mobile, TaskListener taskListener) {
        this.mobile = mobile;
        this.taskListener = taskListener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        customersGrpc.customersBlockingStub stub = null;
        try {
            String host = "";
            int port = 0;
            stub = customersGrpc.newBlockingStub(ManagedChannelBuilder.forAddress(host, port).build());
            final Isregistered.request request = Isregistered.request.newBuilder().setMobile(mobile).build();
//            Log.i(GRPCTAG, request.toString());
            final Isregistered.response isregistered = stub.isregistered(request);
//            Log.i(GRPCTAG, isregistered.toString());

            return isregistered.getIsregistered();

        } catch (StatusRuntimeException e) {
            return false;
        } finally {
            if (stub != null) {
                ((ManagedChannel) stub.getChannel()).shutdown();
            }
        }
//        return null;
    }

    @Override
    protected void onPostExecute(Boolean isRegistered) {
        super.onPostExecute(isRegistered);
        taskListener.onResult(isRegistered);
    }

    interface TaskListener {
        void onResult(boolean isRegistered);
    }
}
