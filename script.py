import json

xml_file = 'm:/NearNeed/app/src/main/res/layout/activity_messages.xml'

with open('m:/NearNeed/people.json', 'r', encoding='utf-8') as f:
    people = json.load(f)

with open(xml_file, 'r', encoding='utf-8') as f:
    lines = f.readlines()

start_idx = -1
end_idx = -1

for i, line in enumerate(lines):
    if '<!-- Conversation: Rachel -->' in line:
        start_idx = i
    if '<!-- Custom Straight Navbar -->' in line:
        end_idx = i
        break

if start_idx == -1 or end_idx == -1:
    print('Indices not found:', start_idx, end_idx)
    exit(1)

closing_idx = end_idx - 3

new_content = []
for i, p in enumerate(people):
    name = p['name']
    msg = p['msg'].replace('&', '&amp;').replace('<', '&lt;').replace('\"', '&quot;').replace(\"'\", \"&apos;\")
    time = p['time']
    unread = p['unread']
    status_bg = p['bg']
    
    id_base = name.split()[0].replace(\"'\",\"\")
    
    blue_dot = ''
    text_color = '#0F172A'
    msg_color = '#0F172A' if unread else '#64748B'
    font_val = 'bold' if unread else 'normal'
    
    if unread:
        blue_dot = '<View android:layout_width=\"10dp\" android:layout_height=\"10dp\" android:background=\"@drawable/bg_dot_green\" android:layout_gravity=\"end\" android:layout_marginTop=\"6dp\" android:backgroundTint=\"#1D5EF3\" />'
        
    s = f'''                <!-- Conversation: {name} -->
                <RelativeLayout android:id=\"@+id/conv{id_base}_{i}\" android:layout_width=\"match_parent\" android:layout_height=\"80dp\" android:paddingStart=\"16dp\" android:paddingEnd=\"16dp\" android:background=\"?attr/selectableItemBackground\" android:gravity=\"center_vertical\">
                    <FrameLayout android:id=\"@+id/avatar{id_base}_{i}\" android:layout_width=\"52dp\" android:layout_height=\"52dp\" android:layout_centerVertical=\"true\">
                        <ImageView android:layout_width=\"52dp\" android:layout_height=\"52dp\" android:src=\"@drawable/bg_image_placeholder\" android:scaleType=\"centerCrop\" />
                        <View android:layout_width=\"12dp\" android:layout_height=\"12dp\" android:layout_gravity=\"bottom|end\" android:background=\"@drawable/{status_bg}\" android:elevation=\"1dp\" />
                    </FrameLayout>
                    <LinearLayout android:layout_width=\"0dp\" android:layout_height=\"wrap_content\" android:layout_toEndOf=\"@id/avatar{id_base}_{i}\" android:layout_toStartOf=\"@+id/time{id_base}_{i}\" android:layout_centerVertical=\"true\" android:layout_marginStart=\"12dp\" android:orientation=\"vertical\">
                        <TextView android:layout_width=\"wrap_content\" android:layout_height=\"wrap_content\" android:text=\"{name}\" android:textColor=\"#0F172A\" android:textSize=\"16sp\" android:textStyle=\"bold\" />
                        <TextView android:layout_width=\"wrap_content\" android:layout_height=\"wrap_content\" android:text=\"{msg}\" android:textColor=\"{msg_color}\" android:textSize=\"14sp\" android:layout_marginTop=\"4dp\" android:textStyle=\"{font_val}\" android:maxLines=\"1\" android:ellipsize=\"end\" />
                    </LinearLayout>
                    <LinearLayout android:id=\"@+id/time{id_base}_{i}\" android:layout_width=\"wrap_content\" android:layout_height=\"wrap_content\" android:layout_alignParentEnd=\"true\" android:layout_centerVertical=\"true\" android:orientation=\"vertical\" android:gravity=\"end\">
                        <TextView android:layout_width=\"wrap_content\" android:layout_height=\"wrap_content\" android:text=\"{time}\" android:textColor=\"#94A3B8\" android:textSize=\"12sp\" />
                        {blue_dot}
                    </LinearLayout>
                </RelativeLayout>
                <View android:layout_width=\"match_parent\" android:layout_height=\"1dp\" android:background=\"#F1F5F9\" android:layout_marginStart=\"80dp\" />
'''
    new_content.append(s)

lines = lines[:start_idx] + [\"\n\".join(new_content) + \"\n\"] + lines[closing_idx:]

with open(xml_file, 'w', encoding='utf-8') as f:
    f.writelines(lines)

print('Success')
