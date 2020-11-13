package com.example.grid3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    String[] fruitNames = {"Loa kẹo kéo","Huỳnh Minh Thuận ","Loa vi tính","Loa kimiso s1","Loa Kẹo Kéo Có Mic Hát Karaoke","Loa kraoke cắm thẻ nhớ"};
    int[] fruitImages = {R.drawable.loa1,R.drawable.loa2,R.drawable.loa3,R.drawable.loa4,R.drawable.loa5,R.drawable.loa6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView=findViewById(R.id.gridview1);

        CustomAdapter customAdapter=new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),fruitNames[position],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",fruitNames[position]);
                intent.putExtra("image",fruitImages[position]);
                startActivity(intent);
            }
        });
        gridView.setOnItemLongClickListener(new ItemLongClickRemove());
    }

    public class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return fruitNames.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view1=getLayoutInflater().inflate(R.layout.row_data,null);
            TextView name=view1.findViewById(R.id.dish);
            ImageView image=view1.findViewById(R.id.image);
            name.setText(fruitNames[position]);
            image.setImageResource(fruitImages[position]);
            return view1;
        }
    }

    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Xóa sản phẩm");
            alertDialogBuilder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }

}
