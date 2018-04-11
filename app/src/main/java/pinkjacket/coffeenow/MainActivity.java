package pinkjacket.coffeenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.coffeeButton) Button mCoffeeButton;
    @BindView(R.id.zipText) EditText mZipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mCoffeeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String location = mZipText.getText().toString();
                Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }
}
