import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.supervend.Review
import java.util.ArrayList

class ListAdapter(context: Context, val layoutResource: Int, userArrayList: ArrayList<Review?>) :
    ArrayAdapter<Review?>(context, layoutResource, userArrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val review: Review? = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResource, parent, false)
        }
        val imageView = convertView!!.findViewById<ImageView>(R.id.profile_pic)
        val userName = convertView.findViewById<TextView>(R.id.personName)
        val lastMsg = convertView.findViewById<TextView>(R.id.lastMessage)
        val time = convertView.findViewById<TextView>(R.id.msgtime)
        imageView.setImageResource(user.imageId)
        userName.setText(user.name)
        lastMsg.setText(user.lastMessage)
        time.setText(user.lastMsgTime)
        return convertView
    }
}